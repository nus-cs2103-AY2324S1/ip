package kevin.taskList;

/**
 * A class to represent the ToDo Task.
 */
public class ToDo extends Task{
    /**
     * Constructor to initialize ToDo.
     * @param isDone This is a boolean to mark whether the ToDo is done or not.
     * @param name This is the name description of the ToDo.
     */
    public ToDo(Boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Returns a string representation of the ToDo Object to be written to the file.
     */
    public String toText() {
        return "Todo - "  + isDone + " - " + name + System.lineSeparator();
    }

    /**
     * {@inheritDoc}
     * @return Returns a string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
