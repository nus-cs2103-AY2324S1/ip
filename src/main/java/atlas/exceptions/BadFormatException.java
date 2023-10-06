package atlas.exceptions;

import atlas.tasks.Task;

/**
 * Exception for inputs that do not follow the formats specified by the commands i.e. task creation commands
 */
public class BadFormatException extends RuntimeException {
    private final Task.Type commandType;
    private final String correctFormat;

    /**
     * Constructor for a BadFormatException
     * @param commandType Type of command called
     * @param correctFormat Recommended format for the command called
     */
    public BadFormatException(Task.Type commandType, String correctFormat) {
        this.commandType = commandType;
        this.correctFormat = correctFormat;
    }

    @Override
    public String getMessage() {
        return commandType + " can only be invoked with the format:\n" + correctFormat;
    }
}
