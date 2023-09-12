package duke.task;

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
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        return "[T]" + status + " " + super.description;
    }

    /**
     * Returns a string representation of the Event object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String outputString = "T | ";
        outputString += (super.isDone ? "1" : "0") + " | ";
        outputString += super.description;
        return outputString;
    }
}
