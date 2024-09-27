package dude.exception;

/**
 * Exception for invalid task index in command.
 */
public class InvalidTaskIndexException extends DudeException {
    /**
     * Constructs new InvalidTaskIndexException.
     *
     * @param index Invalid task index from input.
     */
    public InvalidTaskIndexException(String index) {
        super(
                String.format("I can't find the task numbered \"%s\".\n"
                        + "Try checking if you've typed the correct task number.",
                        index)
        );
    }
}
