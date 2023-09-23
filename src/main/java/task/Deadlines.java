package task;

/**
 * Object class for instances of deadline tasks
 */
public class Deadlines extends Task {
    protected String by;

    /**
     * Public constructor of the class
     * @param description description of the task
     * @param by date of the deadline
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor to create classes with known status
     * @param status done status of task
     * @param description description of the task
     * @param by date of the deadline
     */
    public Deadlines(boolean status, String description, String by) {
        super(status, description);
        this.by = by;
    }

    /**
     * Overriden toString implementation for a deadline task
     * @return String representing a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * formats the properties of the object into a single string line for saving
     * @return String of formatted properties
     */
    @Override
    public String dataFormat() {
        return "deadline/"
                + super.isDone.toString() + "/"
                + super.description + "/"
                + this.by;
    }
}
