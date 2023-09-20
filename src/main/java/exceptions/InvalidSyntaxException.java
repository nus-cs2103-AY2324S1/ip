package exceptions;

/**
 * Exception class for invalid tasks syntax
 */
public class InvalidSyntaxException extends BocchiException {
    static final String INVALID_DEADLINE_DATE_ERROR_MSG =
            "Please use the following syntax when creating a deadline:\n"
                    + "{activity} /by {date}";
    static final String INVALID_EVENT_DATE_ERROR_MSG =
            "Please use the following syntax when creating an event:\n"
                    + "{activity} /from {start time} /to {end time}";
    static final String UNEXPECTED_ERROR_IN_ERROR_MSG =
            "Unexpected error occurred in exceptions.InvalidSyntaxException!";

    public InvalidSyntaxException(String taskType) {
        super(generateErrorMessage(taskType));
    }

    private static String generateErrorMessage(String taskType) {
        switch (taskType) {
        case "deadline":
            return INVALID_DEADLINE_DATE_ERROR_MSG;
        case "event":
            return INVALID_EVENT_DATE_ERROR_MSG;
        default:
            return UNEXPECTED_ERROR_IN_ERROR_MSG;
        }
    }
}
