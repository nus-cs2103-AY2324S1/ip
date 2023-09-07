package chatterchicken.data.task;

public class ToDo extends Task {

    public ToDo(String taskDescription) {
        super(taskDescription, taskDescription);
    }

    /**
     * Generates the formatted representation of the todo task.
     * The format includes the task status, task type, and description.
     *
     * @return The formatted representation of the todo task.
     */
    @Override
    public String getTaskForPrinting() {
        return String.format("[%s][T] %s", super.checkDone(), super.getName());
    }

    public String getInput() {
        return "todo " + super.getTaskDescription();
    }
}