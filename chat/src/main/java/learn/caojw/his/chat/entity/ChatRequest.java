package learn.caojw.his.chat.entity;

import lombok.Data;

import java.util.Collection;

@Data
public class ChatRequest {
    private String model;
    private Collection<ChatMessage> messages;
    private boolean stream;
}
