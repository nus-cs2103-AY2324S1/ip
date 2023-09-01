package pau.exception;

public class DeadlineNoEndException extends Exception {
    public DeadlineNoEndException(String errorMessage) {
        int cryingEmojiUnicode = 0x1F62D;

        System.out.println("when is this due" + new String(Character.toChars(cryingEmojiUnicode)));
        System.out.println(errorMessage);
    }
}
