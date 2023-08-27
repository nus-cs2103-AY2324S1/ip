import java.time.Instant;

/**
 * The read only instance for a single message.
 */
public class ChatMessage {
    /**
     * The message sender for a conversation message
     */
    public enum SenderType {
        CHATBOT,
        USER
    }

    private long timestamp;
    private SenderType senderType;
    private String message;

    ChatMessage(SenderType senderType, String message) {
        this.timestamp = Instant.now().getEpochSecond();
        this.senderType = senderType;
        this.message = message;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public SenderType getSenderType() {
        return this.senderType;
    }

    public String getMessage() {
        return this.message;
    }
}