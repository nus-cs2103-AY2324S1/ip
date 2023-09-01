package duck.exceptions;

public class FileIOException extends DuckException {
    public FileIOException(String str) {
        super("File I/O error " + str);
    }
}
