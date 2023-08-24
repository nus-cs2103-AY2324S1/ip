public class ToDo extends Task {
    ToDo(String task) throws DukeException {
        super(task);
        if (task.isBlank() || task.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
