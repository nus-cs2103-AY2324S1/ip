package duke;

/**
 * The Event class extends from the Task class.
 */
public class Event extends Task {

    /**
     * Creates a Event object.
     *
     * @param done Boolean representation of completion.
     * @param name Name of event task.
     */
    Event(boolean done, String name) {
        super(done, name);
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
        return "E";
    }

    /**
     * Returns a String representation of the Event object.
     *
     * @return "[E] [X]/[ ] [name] (from: A to: B)
     */
    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
