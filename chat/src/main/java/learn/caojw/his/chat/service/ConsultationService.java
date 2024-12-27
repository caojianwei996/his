package learn.caojw.his.chat.service;

import learn.caojw.his.chat.endpoint.DoctorEndpoint;
import learn.caojw.his.chat.endpoint.PatientEndpoint;
import learn.caojw.his.chat.entity.ChatMessage;
import learn.caojw.his.chat.entity.ChatRequest;
import learn.caojw.his.chat.entity.ChatResponse;
import learn.caojw.his.chat.entity.Message;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 咨询业务
 *
 * @author 曹健伟
 * @version 1.0-SNAPSHOT
 */
public class ConsultationService {
    private static final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:11434/api/chat")
            .build();
    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(128);
    private static final ChatMessage system;


    static {
        system = new ChatMessage();
        system.setRole("system");
        system.setContent("你是一个在线问诊系统中的AI助理，负责在医生接诊前与患者沟通，稳定患者目前的健康和情绪状况");
    }

    public static boolean enqueue(String id) {
        try {
            return queue.offer(id, 5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String dequeue() {
        String id;
        try {
            do {
                id = queue.take();
            } while (PatientEndpoint.getEndPoint(id) == null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static Message autoResponse(String message) {
        ChatRequest request = new ChatRequest();
        request.setModel("qwen2.5:latest");
        ChatMessage user = new ChatMessage();
        user.setRole("user");
        user.setContent(message);
        request.setMessages(List.of(system, user));
        request.setStream(false);
        ChatResponse response = restClient
                .post()
                .body(request)
                .retrieve()
                .body(ChatResponse.class);
        if (response != null && response.getMessage() != null) {
            return Message.user(response.getMessage().getContent());
        } else {
            return Message.system("服务异常");
        }
    }

    public static void messageToDoctor(String id, Message message) {
        DoctorEndpoint.getEndPoint(id).sendMessage(message);
    }

    public static void messageToInhabitant(String id, Message message) {
        PatientEndpoint.getEndPoint(id).sendMessage(message);
    }
}
