package chatterbot.data;

/**
 * Represents a task that is an event, with start and end times.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Invalid input! No task description.");
        }
    }

    /**
     * Returns the description, start and end time of the event task.
     * @return String This is the event description, start and end time in the format it will be displayed in,
     *     in the list.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    /**
     * Returns the input for the Chatterbot.txt file in the same format as was entered by the user.
     * @return String This is the formatted line to add to the ChatterBot.txt file.
     */
    @Override
    public String forFile() {
        return "event " + this.description + "/from " + this.from + "/to " + this.to;
    }
}