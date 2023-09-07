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
}