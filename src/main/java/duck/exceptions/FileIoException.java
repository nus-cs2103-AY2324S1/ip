package duck.exceptions;

public class FileIoException extends DuckException {
    public FileIoException(String str) {
        super("File I/O error " + str);
    }
}
