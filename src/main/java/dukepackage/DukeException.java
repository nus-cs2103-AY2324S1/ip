package dukepackage;

import java.nio.charset.StandardCharsets;

/**
 * Represents a Duke Exception. A DukeException object
 * will contain an error message represented by
 * a string.
 */
public class DukeException extends Exception {

    private static byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};

    /**
     * Constructs the Duke Exception and sets the
     * error message for the exception.
     *
     * @param msg string printed when the exception is caught.
     */
    public DukeException(String msg) {
        super(new String(DukeException.emojiByteCode, StandardCharsets.UTF_8) + msg);
    }
}
