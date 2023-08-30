public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor to create an Event object.
     *
     * @param description The task description.
     * @param from The task's start date/time.
     * @param to The task's end date/time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Method to get the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns deadline task information in format for saving.
     * Format is E | [1 if completed, 0 if not completed] | [task description] | [from] | [to]
     *
     * @return Deadline task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return String.format("E | %1s | %2s | %3s", super.getInformationForSaving(), this.from, this.to);
    }
}
