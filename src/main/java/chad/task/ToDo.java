package chad.task;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        String taskType = "[T]";
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String description = " " + super.description;

        return taskType + status + description;
    }

    /**
     * Returns a string representation of the Event object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String taskType = "T | ";
        String status = (super.isDone ? "1" : "0") + " | ";
        String description = super.description;

        return taskType + status + description;
    }

    /**
     * Compares the ToDo object with a Task object, if Task object is
     * an Event object, compare the start dates of both objects.
     * @param that the object to be compared.
     * @return Positive integer if the other Task object is a Deadline Task,
     *     Event object, or a ToDo object with a lower lexicographical order
     *     with an earlier start date; Otherwise, a negative integer if the
     *     other Task is a ToDo Task with a higher lexicographical order.
     */
    @Override
    public int compareTo(Task that) {
        if (that instanceof ToDo) {
            return this.description.charAt(0) - that.description.charAt(0);
        }
        return this.toString().charAt(1) - that.toString().charAt(1);
    }
}
