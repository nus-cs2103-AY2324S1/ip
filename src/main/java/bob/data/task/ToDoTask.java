package bob.data.task;

/**
 * Represent a ToDoTask that is contained in the TaskList.
 */
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

    /**
     * Checks if this ToDoTask is the same as a specified object.
     * @param obj The object to be compared with.
     * @return true if they are both the same instance or have the same contents.
     *         false if they have different contents.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ToDoTask) {
            ToDoTask object = (ToDoTask) obj;

            if (super.equals(object)) {
                return true;
            }
        }
        return false;
    }
}
