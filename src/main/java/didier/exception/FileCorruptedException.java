package didier.exception;

public class FileCorruptedException extends DidierException {

    public FileCorruptedException() {
        super("The task file was corrupted. Please delete the file.");
    }
}
