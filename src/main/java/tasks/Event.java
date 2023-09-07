package tasks;

/**
 * A child class to Task, for this in particular it is for events tasks.
 */
public class Event extends Task {
    private final String start;
    private final String end;
    /**
     * A Constructor method.
     * @param name the name of the task.
     * @param start the start date of the task.
     * @param end the end date of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        super.setType("E");
    }

    /**
     * A method that will represent the task.
     *
     * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
