package taskmate.exceptions;

/**
 * The FileCorruptedException class is a child class of the Exception class. It is thrown when the save file
 * is tampered with, causing some lines to be impossible to parse.
 */
public class FileCorruptedException extends Exception {
    public FileCorruptedException(String message) {
        super(message);
    }
}
