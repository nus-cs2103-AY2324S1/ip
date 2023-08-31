package bee;

/**
 * Custom exception class for handling exceptions specific to the Bee chatbot.
 * Extends the base class Exception to add custom behavior and formatting for error messages.
 */
public class BeeException extends Exception {

    /**
     * Constructs a BeeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public BeeException(String message) {
        super(message);
    }

    /**
     * Converts the BeeException to a formatted string representation.
     *
     * @return Formatted string representation of the BeeException.
     */
    @Override
    public String toString() {
        return "    _  _\n" +
                "   | )/ )\n" +
                "\\\\ |//,' __\n" +
                "(\")(_)-\"()))=-\n" +
                "   (\\\\ " + super.getMessage();
    }
}
