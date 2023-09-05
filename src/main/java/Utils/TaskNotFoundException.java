package Utils;

public class TaskNotFoundException extends DukeException {
    protected TaskNotFoundException() {
        super("I'm sorry, but your input number is out of range of the current tasks");
    }
}
