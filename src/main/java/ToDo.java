public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    /**
     * Generates the formatted representation of the todo task.
     * The format includes the task status, task type, and description.
     *
     * @return The formatted representation of the todo task.
     */
    @Override
    public String getTask() {
        return String.format("[%s][T] %s", super.checkDone(), super.getName());
    }

    @Override
    public String getTaskType() {
        return "todo";
    }
}
