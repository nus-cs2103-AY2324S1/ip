package exceptions;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int listSize) {
        super(String.format("Invalid index format.\n"
                + "Format should be: mark <task index> OR unmark <task index> "
                + "OR delete <task index>\n"
                + "where index is from 1 to %d.", listSize));
    }
}
