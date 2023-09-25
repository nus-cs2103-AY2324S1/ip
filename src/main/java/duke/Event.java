package duke;

/**
 * The Event class extends from the Task class.
 */
public class Event extends Task {

    private static final String TASK_TYPE = "E";

    /**
     * Creates a Event object.
     *
     * @param isDone Boolean representation of completion.
     * @param name Name of event task.
     */
    Event(boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Creates a Deadline object with default false completion.
     *
     * @param name Name of deadline Task.
     */
    Event(String name) {
        super(name);
    }

    /**
     * Returns the type of task in String format.
     *
     * @return "E" for Event.
     */
    @Override
    public String taskType() {
        return TASK_TYPE;
    }

    /**
     * Returns a String representation of the Event object.
     *
     * @return "[E] [X]/[ ] [name] (from: A to: B)
     */
    @Override
    public String toString() {
        return String.format("[%s] ", TASK_TYPE) + super.toString();
    }
}
