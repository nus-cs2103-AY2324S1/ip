package duke;

public class DukeNoExistingTasksException extends DukeLoadTasksException{
    public DukeNoExistingTasksException() {
        super("No existing save file!");
    }
}
