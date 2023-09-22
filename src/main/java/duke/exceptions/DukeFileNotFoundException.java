package duke.exceptions;

/**
 * FileNotFoundException for Duke.
 */
public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException(String file) {
        super("\nOOPS!!! Could not find the file " + file);
    }
}
