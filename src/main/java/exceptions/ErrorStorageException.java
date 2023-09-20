package exceptions;
/**
 * Custom exception class to handle cases where there is an error loading text file data
 */
public class ErrorStorageException extends Exception {

    /**
     * Public constructor for throwing ErrorStorageException
     * @param message string of text
     */
    public ErrorStorageException(String message) {
        super("Macho! There is an error loading existing storage of tasks list!\n" + message);
    }
}
