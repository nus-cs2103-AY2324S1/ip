package chatbuddy;

public class ChatBuddyException extends Exception {
    protected String description;

    /**
     * Constructor to create a ChatBuddyException.
     * @param description A description of the error.
     */
    public ChatBuddyException(String description) {
        this.description = description;
    }

    /**
     * Method to get the string description of the exception.
     * @return the string description of the exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + description;
    }

    @Override
    public String getMessage() {
        return toString();
    }
}
