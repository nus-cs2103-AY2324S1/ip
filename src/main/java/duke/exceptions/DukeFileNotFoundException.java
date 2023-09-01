package duke.exceptions;

public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException(String file) {
        super("\nOOPS!!! Could not find the file " + file);
    }
}
