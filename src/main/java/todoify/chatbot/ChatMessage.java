package todoify.chatbot;

import java.time.Instant;

/**
 * The read-only instance for a single message.
 */
public class ChatMessage {
    /**
     * The message sender type for a message in a conversation.
     */
    public enum SenderType {
        CHATBOT, USER
    }

    private final long timestamp;
    private final SenderType senderType;
    private final String message;


    /**
     * Constructor for a chat message with the current time.
     *
     * @param senderType The type of the sender.
     * @param message    The message sent by the sender.
     */
    public ChatMessage(SenderType senderType, String message) {
        this.timestamp = Instant.now().getEpochSecond();
        this.senderType = senderType;
        this.message = message;
    }

    /**
     * Returns the timestamp of this message, in Unix epoch seconds.
     *
     * @return The timestamp of this message.
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns the type of this sender.
     *
     * @return The sender type.
     */
    public SenderType getSenderType() {
        return this.senderType;
    }

    /**
     * Returns the message contents.
     *
     * @return The message contents as a String.
     */
    public String getMessage() {
        return this.message;
    }
}
