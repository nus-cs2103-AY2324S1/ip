package chatterbot.exceptions;

/**
 * This class inherits from ChatterBotException.
 * An instance is created when no task description is given.
 */
public class NoTaskDescriptionException extends ChatterBotException {
    public NoTaskDescriptionException() {
        super("No task description provided! No task added.");
    }
}
