package main.java.tasks;

public class JukeEvent extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[E] ";

    /** Start Time. */
    private String start;

    /** End Time. */
    private String end;

    /**
     * Constructor used to create an event.
     *
     * @param taskName Task description
     */
    public JukeEvent(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return JukeEvent.TASK_DESCRIPTOR + super.toString() + " (from " + start + " to " + end + ")";
    }
}
