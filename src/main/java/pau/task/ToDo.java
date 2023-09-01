package pau.task;

/**
 * Represents a ToDo task that only has a description of the task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description of the task.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "T" + delimiter + status + delimiter + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
