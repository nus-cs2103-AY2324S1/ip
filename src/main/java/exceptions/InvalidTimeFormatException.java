package exceptions;

/**
 * Custom exception class to handle cases where input date and time is in invalid format.
 */
public class InvalidTimeFormatException extends Exception {

    /**
     * Public constructor for throwing InvalidTaskDescriptionException
     * @param message string of text
     */
    public InvalidTimeFormatException(String message, String divider) {
        super("Macho! The input time format of a " + message + " is wrong, macho! Please enter in format "
               + "yyyy-MM-dd HH:mm, macho!\n" + divider);
    }
}
