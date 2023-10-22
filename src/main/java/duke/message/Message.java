package duke.message;

/**
 * Represents the Message.
 */
public abstract class Message {
    /**
     * Creates the message for Message.
     * @param messages String messages to be printed.
     * @return String representation of Message.
     */
    protected String createMessage(String... messages) {
        StringBuilder s = new StringBuilder();
        for (String message: messages) {
            s.append(message);
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Returns String representation of Message.
     */
    public abstract String send();
}
