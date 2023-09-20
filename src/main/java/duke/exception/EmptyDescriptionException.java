package duke.exception;

public class EmptyDescriptionException extends Exception {
    /**
     * Constructs a EmptyDescriptionException object which happens when user does not provide a valid description.
     *
     * @param taskType The type of the tasks the user is trying to create.
     * @param format   Example of how a valid command looks like.
     */
    public EmptyDescriptionException(String taskType, String format) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.\nExample: %s", taskType, format));
    }
}
