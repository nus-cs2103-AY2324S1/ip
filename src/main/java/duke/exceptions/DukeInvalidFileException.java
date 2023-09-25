package duke.exceptions;

public class DukeInvalidFileException extends DukeException{
    public DukeInvalidFileException() {
        super("An exception occurred while looking for the file.");
    }
}
