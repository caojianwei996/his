package learn.caojw.his.chat.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatResponse {
    private String model;
    private LocalDateTime createAt;
    private ChatMessage message;
    private String doneReason;
    private boolean done;
    private Long totalDuration;
    private Long loadDuration;
    private Long promptEvalCount;
    private Long evalCount;
    private Long evalDuration;
}