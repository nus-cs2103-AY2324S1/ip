package duke.exceptions;

public class NoIndexException extends Exception {

    public NoIndexException(String message) {
        super(String.format("☹ OOPS!!! The index %s does not exist.", message));
    }
}
