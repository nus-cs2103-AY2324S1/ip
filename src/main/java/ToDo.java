public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTask() {
        return "To Do ->" + super.getTask();
    }
}
