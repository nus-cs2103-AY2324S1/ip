package noac.task;


/**
 * To do task which extends the task class.
 */
public class Todo extends Task {

    /**
     * Constructor to initialise the task.
     *
     * @param description Description on what the task is.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the task to string.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    @Override
    public String printToFile() {
        return "T|" + super.printToFile();
    }

}
