package duke;

/**
 * The ToDo class represents a Task with a duration.
 */
public class ToDo extends Task {
    /**
     * Instantiates an instance of ToDo.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "T | " + mark + " | " + super.writeToFile();
    }
}
