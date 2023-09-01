package exceptions;

public class InvalidSyntaxException extends BocchiException {
    public InvalidSyntaxException(String taskType) {
        super(generateErrorMessage(taskType));
    }

    private static String generateErrorMessage(String taskType) {
        switch (taskType) {
        case "deadline":
            return "Please use the following syntax when creating a deadline:\n"
                    + "{activity} /by {date}";
        case "event":
            return "Please use the following syntax when creating an event:\n"
                    + "{activity} /from {start time} /to {end time}";
        }
        return "Unexpected error occurred in exceptions.InvalidSyntaxException!";
    }
}
