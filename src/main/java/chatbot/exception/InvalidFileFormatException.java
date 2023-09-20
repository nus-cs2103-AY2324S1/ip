package chatbot.exception;

public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException() {
        super("Error loading tasks from file. Please check the format of the tasks in the data file!");
    }
}
