public class Event extends Task{

    private String from;
    private String to;

    /**
     * Constructs a event with a given description. Completion of the task
     * is false by default
     *
     * @param description The description of the task.
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }



    /**
     * Returns a formatted string of the status of the task
     * @return String containing completion status, type, description, from
     * and to of task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string formatted in the way it is to be saved
     * @return Formatted string to be written into file
     */
    public String toSaveFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description + " | "
                + this.from + "-" + this.to;
    }
}
