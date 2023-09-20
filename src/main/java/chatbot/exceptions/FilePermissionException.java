package chatbot.exceptions;

/**
 * Class that represents an exception due to insufficient access to the data file.
 */
public class FilePermissionException extends LocalFileException {
    private String filePath;

    public FilePermissionException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "OOPS, an error occurred when accessing / creating / writing a local file.\n"
                + "The file path is " + this.filePath
                + "\nFor this session, previous data cannot be read, \nand current data cannot be stored.";
    }
}
