import exception.DukeException;

public class ToDo extends Task {
    public ToDo(String task)throws DukeException {
        super(task);
        if (task.isBlank() || task.isEmpty()) {
            throw new DukeException("The description of a To-Do task cannot be empty.");
        }
    }

    @Override
    public String getStatus() {
        return "[To-Do]" + super.getStatus();
    }

    @Override
    public String toFileString() {
        return super.isDone ? ("T | 1 | " + super.task) : ("T | 0 | " + super.task);
    }
}