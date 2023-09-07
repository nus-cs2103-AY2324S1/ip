package prts.command;

import prts.PrtsException;

/**
 * Signals that an error has occurred when parsing the user input.
 * This error can be of various types, and a different error message is displayed according to the
 * input type of error. A full list of error types is listed in the enum ExceptionType.
 */
public class ParsingException extends PrtsException {

    /**
     * A list of all error types accounted for by the ParsingException class.
     */
    enum ExceptionType {
        IMPROPER_FORMAT,
        MISSING_BODY,
        MISSING_DETAIL,
        MISSING_INDEX,
        NOT_A_NUMBER,
        EXCESS_INPUT
    }

    ExceptionType exceptionType;

    /**
     * Constructs a ParsingException given the error type.
     * @param exceptionType The type of parsing error encountered.
     */
    public ParsingException(ExceptionType exceptionType) {
        super("There was an error in parsing your input.");
        this.exceptionType = exceptionType;
    }

    /**
     * Returns an error message that changes depending on the type of parsing error this is.
     * @return The parsing error message.
     */
    @Override
    public String getMessage() {
        switch (exceptionType) {
            case IMPROPER_FORMAT:
                return "Please format your description properly.";

            case MISSING_BODY:
                return "I can't read your mind. Do add a description.";

            case MISSING_DETAIL:
                return "I can't read your mind. Do add more details.";

            case MISSING_INDEX:
                return "I can't read your mind. Provide a suitable index.";

            case NOT_A_NUMBER:
                return "I can't understand that number.";

            case EXCESS_INPUT:
                return "Sorry, you're speaking a little fast. Could you repeat just the first word?";

            default:
                return "Something went wrong.";
        }
    }

}
