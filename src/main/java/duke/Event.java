package duke;

/**
 * The duke.Event class represents a task with a start date and end date.
 * It extends the duke.Task class.
 * Adds a 'from' and 'to' fields to store the state date and end date respectively of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for duke.Event class.
     *
     * @param description the description of the task
     * @param from        the start date of the task
     * @param to          the end date of the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides toString method from duke.Task
     * returns a String representation of duke.Event task.
     *
     * @return The String representation of duke.Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Retrieves the start date of an event.
     *
     * @return the String representation of the start date.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * An accessor method to retrieve the end date of an event.
     *
     * @return the String representation of the end date.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Check if the input task is equals to the current Event instance.
     *
     * @param newTask The task to be compared to
     * @return True if the both task are the same
     */
    @Override
    public boolean isDuplicate(Task newTask) {
        if (newTask instanceof Event) {
            Event addTask = (Event) newTask;
            return addTask.getDescription().equals(this.description)
                    && addTask.getFrom().equals(this.from)
                    && addTask.getTo().equals(this.to);
        }
        return false;
    }

    /**
     * Returns a String representation of Event task in file format.
     *
     * @return The String representation of task to be saved into a file.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + from + " | " + to;
    }
}
