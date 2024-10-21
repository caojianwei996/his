package learn.caojw.his.chat.service.implement;

import learn.caojw.his.chat.api.ChatApi;
import learn.caojw.his.chat.entity.ChatMessage;
import learn.caojw.his.chat.entity.ChatRequest;
import learn.caojw.his.chat.entity.ChatResponse;
import learn.caojw.his.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceVersion1 implements ChatService {
    private final ChatApi chatApi;

    @Override
    public String chat(String message) {
        ChatRequest request = new ChatRequest();
        request.setModel("qwen2.5:latest");
        ChatMessage system = new ChatMessage();
        system.setRole("system");
        system.setContent("你是医院接诊台的医生，负责帮助患者选择科室和提供一些简单的帮助");
        ChatMessage user = new ChatMessage();
        user.setRole("user");
        user.setContent(message);
        request.setMessages(List.of(system, user));
        request.setStream(false);
        ChatResponse response = chatApi.request(request);
        return response.getMessage().getContent();
    }
}
