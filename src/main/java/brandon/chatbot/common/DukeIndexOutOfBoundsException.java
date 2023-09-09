package brandon.chatbot.common;

/**
 * Represents an exception where the user tries to search for a task index that is out of bounds.
 */
public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException() {
        super("    The index is way too big... I think...\n--------------------------------");
    }
}
