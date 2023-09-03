package duke;

/**
 * The ToDo class extends from the Task class.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object.
     *
     * @param done Boolean representation of completion.
     * @param name Name of ToDo task.
     */
    ToDo(boolean done, String name) {
        super(done, name);
    }

    /**
     * Creates a ToDo object with default false completion.
     *
     * @param name Name of ToDo Task.
     */
    ToDo(String name) {
        super(name);
    }

    /**
     * Returns the type of task in String format.
     *
     * @return "T" for ToDo.
     */
    @Override
    public String taskType() {
        return "T";
    }

    /**
     * Returns a String representation of the ToDo object.
     *
     * @return "[T] [X]/[ ] [name]
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
