package learn.caojw.his.chat.endpoint;

import com.alibaba.fastjson2.JSON;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import learn.caojw.his.chat.entity.Message;
import learn.caojw.his.chat.service.ConsultationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 病人端点
 *
 * @author 曹健伟
 * @version 1.0-SNAPSHOT
 */
@ServerEndpoint(value = "/patient")
@Slf4j
public class PatientEndpoint {
    private static final Map<String, PatientEndpoint> endpointMap = new ConcurrentHashMap<>();
    private Session session;
    private String id;

    public static PatientEndpoint getEndPoint(String id) {
        return endpointMap.get(id);
    }

    @OnOpen
    @SneakyThrows
    public void open(Session session) {
        this.session = session;
        sendMessage(Message.system("欢迎使用在线问诊系统"));
        String id = session.getId();
        if (ConsultationService.enqueue(id)) {
            endpointMap.put(id, this);
            sendMessage(Message.system("排队成功，请耐心等待，且您可以先与AI助理进行简单的沟通"));
        } else {
            sendMessage(Message.system("排队失败，请稍后重试，但您仍可以与AI助理进行简单的沟通"));
        }
    }

    public void connect(String id) {
        sendMessage(Message.system("医生已接诊"));
        this.id = id;
    }

    @OnMessage
    @SneakyThrows
    public void message(String message) {
        if (id == null) {
            sendMessage(ConsultationService.autoResponse(message));
        } else {
            ConsultationService.messageToDoctor(id, Message.user(message));
        }
    }

    public void sendMessage(Message message) {
        session.getAsyncRemote().sendText(JSON.toJSONString(message));
    }

    public void disconnect() {
        this.id = null;
    }

    @OnClose
    @SneakyThrows
    public void close() {
        endpointMap.remove(session.getId());
        if (id != null) {
            ConsultationService.messageToDoctor(id, Message.system("对方已离开"));
            DoctorEndpoint.getEndPoint(id).disconnect();
            disconnect();
        }
    }

    @OnError
    @SneakyThrows
    public void error(Throwable throwable) {
        log.warn("Session {}:{}", session.getId(), throwable.getMessage(), throwable);
        session.close();
        disconnect();
    }
}
