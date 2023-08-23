public class ToDo extends Task {
    public ToDo(String taskName) throws EmptyToDoException {
        super(taskName);
        if (taskName.isEmpty()) {
            throw new EmptyToDoException("empty todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
