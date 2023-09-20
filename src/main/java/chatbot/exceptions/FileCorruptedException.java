package chatbot.exceptions;

/**
 * Class that represents an exception due to the data file containing invalid data.
 */
public class FileCorruptedException extends LocalFileException {
    private String filePath;

    public FileCorruptedException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "OOPS, the file " + this.filePath + " seems to be corrupted.\n"
                + "Initiated with empty task list.";
    }
}
