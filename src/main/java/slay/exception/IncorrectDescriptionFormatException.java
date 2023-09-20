package slay.exception;

/**
 * Signals a wrong format of description for an AddCommand.
 */
public class IncorrectDescriptionFormatException extends Exception {
    public IncorrectDescriptionFormatException() {
        super("Error: Cannot understand the description format.");
    }
}
