package learn.caojw.his.chat.controller;

import learn.caojw.his.chat.service.ChatService;
import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public Response<String> chat(@RequestBody Request<String> request) {
        return Response.ok(chatService.chat(request.data()));
    }
}
