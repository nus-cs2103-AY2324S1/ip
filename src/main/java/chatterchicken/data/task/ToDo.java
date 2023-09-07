package chatterchicken.data.task;

/**
 * The ToDo class represents a to-do task in the task management application.
 * It extends the base Task class and includes a description of the task.
 * ToDo tasks are used to represent tasks without specific deadlines or start times.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the provided task description.
     *
     * @param taskDescription The description of the to-do task.
     */
    public ToDo(String taskDescription) {
        super(taskDescription, taskDescription);
    }

    /**
     * Generates the formatted representation of the to-do task for display purposes.
     * The format includes the task status, task type, and description.
     *
     * @return The formatted representation of the to-do task.
     */
    @Override
    public String getTaskForPrinting() {
        return String.format("[%s][T] %s", super.checkDone(), super.getName());
    }

    /**
     * Gets the input representation of the to-do task.
     * The input format is suitable for creating and parsing to-do tasks.
     *
     * @return The input representation of the to-do task.
     */
    public String getInput() {
        return "todo " + super.getTaskDescription();
    }
}