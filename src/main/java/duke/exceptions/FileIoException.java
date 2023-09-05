package duke.exceptions;

public class FileIoException extends Exception {
    private static final String FILEIO_ERROR_MESSAGE = "OOPS!!! Errors in handling the file ...\n";

    public FileIoException(String error) {
        super(FILEIO_ERROR_MESSAGE + error);
    }
}
