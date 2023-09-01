package pau.exception;

/**
 * Exception for an invalid end date when creating a deadline task.
 */
public class DeadlineNoEndException extends Exception {
    /**
     * Constructs a DeadlineNoEndException with an error message.
     *
     * @param errorMessage Error message that is printed when exception is thrown.
     */
    public DeadlineNoEndException(String errorMessage) {
        int cryingEmojiUnicode = 0x1F62D;

        System.out.println("when is this due" + new String(Character.toChars(cryingEmojiUnicode)));
        System.out.println(errorMessage);
    }
}
