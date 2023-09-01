package chatbuddy;

public class ChatBuddyException extends Exception {
    protected String description;

    /**
     * Creates an instance of a ChatBuddyException.
     *
     * @param description The description of the error.
     */
    public ChatBuddyException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + description;
    }

    @Override
    public String getMessage() {
        return toString();
    }
}
