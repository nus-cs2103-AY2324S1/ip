package duke;

/**
 * A subclass of Task
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Public constructor of Event class.
     *
     * @param name extracted from the original input
     * @param from extracted from the original input
     * @param to extracted from the original input
     * @param input the input that user keyed in
     */
    public Event(String name, String from, String to, String input) {
        super(name, input);
        this.from = from;
        this.to = to;
    }

    /**
     * String Representation of the Event class.
     *
     * @return String that contains the Event name, timeframe and flags.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return  "[E][ ] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        } else {
            return "[E][X] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        }
    }
}
