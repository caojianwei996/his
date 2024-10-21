package learn.caojw.his.chat.api;

import learn.caojw.his.chat.entity.ChatRequest;
import learn.caojw.his.chat.entity.ChatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "chat-service", url = "http://localhost:11434", path = "/api")
public interface ChatApi {
    @PostMapping("/chat")
    ChatResponse request(@RequestBody ChatRequest request);
}
