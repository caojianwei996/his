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
 * 医生端点
 *
 * @author 曹健伟
 * @version 1.0-SNAPSHOT
 */
@ServerEndpoint(value = "/doctor")
@Slf4j
public class DoctorEndpoint {
    private static final Map<String, DoctorEndpoint> endpointMap = new ConcurrentHashMap<>();
    private Session session;
    private String id;

    public static DoctorEndpoint getEndPoint(String id) {
        return endpointMap.get(id);
    }

    @OnOpen
    @SneakyThrows
    public void open(Session session) {
        this.session = session;
        sendMessage(Message.system("欢迎使用在线问诊系统"));
        String id = session.getId();
        connect(ConsultationService.dequeue());
        if (this.id == null) {
            sendMessage(Message.system("暂无居民问诊"));
        } else {
            sendMessage(Message.system("已接诊患者"));
            PatientEndpoint.getEndPoint(this.id).connect(id);
        }
        endpointMap.put(id, this);
    }

    public void connect(String id) {
        this.id = id;
    }

    @OnMessage
    @SneakyThrows
    public void message(String message) {
        if (id == null) {
            sendMessage(Message.system("您还未接诊，请先接诊"));
        } else {
            ConsultationService.messageToInhabitant(id, Message.user(message));
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
            ConsultationService.messageToInhabitant(id, Message.system("对方已离开"));
            PatientEndpoint.getEndPoint(id).disconnect();
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
