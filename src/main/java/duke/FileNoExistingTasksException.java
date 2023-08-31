package duke;

public class FileNoExistingTasksException extends FileLoadException {
    public FileNoExistingTasksException(String message) {
        super(message);
    }
}
