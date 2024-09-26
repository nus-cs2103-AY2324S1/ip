package tasks;

/**
 * The class that will create an Event line task.
 * This class extends from the tasks class.
 */
public class Event extends Task {
    private final String start;
    private final String end;
    /**
     * Constructs the method.
     *
     * @param name The name of the task.
     * @param start The start date of the task.
     * @param end The end date of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        super.setType("E");
    }

    /**
     * Represents the task.
     *
     * @return The syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
