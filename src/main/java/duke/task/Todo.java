package duke.task;

public class Todo extends Task {


    /**
     * Constructor of Todo Class.
     * @param description Description of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String that should be written in the file that stores
     * the Tasks.
     * @return The String that should be written in the file that stores
     *      * the Tasks.
     */
    @Override
    public String toWrite() {
        return "[T]" + super.toWrite();
    }

    /**
     * Returns the String that should be printed.
     * @return the String that should be printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
