package tong.exception;

/**
 * Signals an error during loading of the data file.
 */
public class FolderNotFoundException extends Exception{
    public FolderNotFoundException(String message) {
        super(message);
    }
}
