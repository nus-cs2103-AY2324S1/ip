package chatbot.exceptions;

public class FilePermissionException extends LocalFileException {
    private String filePath;

    public FilePermissionException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "\tOOPS, an error occurred when accessing / creating / writing a local file.\n"
                + "\tThe file path is " + this.filePath
                + "\n\tFor this session, previous data cannot be read, and current data cannot be stored.";
    }
}
