package Utils;

public class InvalidFileDataException extends DukeException {
    protected InvalidFileDataException() {
        super("File data integrity is compromised.");
    }
}
