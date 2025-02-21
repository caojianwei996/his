package learn.caojw.his.chat.entity;

/**
 * 消息实体
 *
 * @author 曹健伟
 * @version 1.0-SNAPSHOT
 */
public record Message(String type, String content) {
    public static Message system(String content) {
        return new Message("system", content);
    }

    public static Message user(String content) {
        return new Message("user", content);
    }
}
