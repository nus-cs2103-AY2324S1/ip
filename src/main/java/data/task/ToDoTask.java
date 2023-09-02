package data.task;
public class ToDoTask extends Task {
    /**
     * Constructs a new ToDoTask based on the specified description.
     *
     * @param description The description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns the type of task as a String.
     *
     * @return The type of task.
     */
    @Override
    public String getType() {
        return "Todo";
    }

    /**
     * Returns a blank String.
     *
     * @return A blank String.
     */
    @Override
    public String getDateTime() {
        return "";
    }

    /**
     * Returns the string representation of the ToDoTask.
     *
     * @return A string representation of this ToDoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
