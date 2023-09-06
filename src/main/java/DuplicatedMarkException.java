/**
 * Signals an error caused by requesting to mark a marked task
 * or requesting to unmark an unmarked task.
 */
public class DuplicatedMarkException extends Exception {
    public DuplicatedMarkException(String message) {
        super(message);
    }
}