package duke;

public class FileCorruptedException extends FileLoadException {
    public FileCorruptedException(String message) {
        super(message);
    }
}
