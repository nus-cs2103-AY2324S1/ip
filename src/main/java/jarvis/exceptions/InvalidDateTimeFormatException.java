package jarvis.exceptions;

/**
 * Represents an exception thrown when an invalid DateTime format is provided to Jarvis.
 */
public class InvalidDateTimeFormatException extends JarvisException {
    /**
     * Returns suggested DateTime Format if an invalid format is entered by the user.
     * @param message
     */
    public InvalidDateTimeFormatException(String message) {
        super("     Please enter a correct DateTime Format Example: \n"
                    + "     \"Nov 12 2022 1200\"" + "\n"
                    + "     \"12 Nov 2022 1200\"" + "\n"
                    + "     \"2022-11-12 1200\"" + "\n"
                    + "     \"12/11/2022 1200\"" + "\n"
                    + "\tYou have entered: " + message + "\n");
    }
}
