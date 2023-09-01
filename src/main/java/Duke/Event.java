package duke;

/**
 * An Event Task that contains a description, a from indicator and a to indicator.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * An Event task constructor.
     * @param description The description of the Event task.
     * @param from the indicated starting time.
     * @param to the indicated ending time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * Returns a String representation of the Event task, including the symbol, status icon, description,
     * and from and to indicators.
     * @return a String to be printed to the user.
     */
    @Override
    public String toString() {
        return ("[E]" + super.getStatusIcon() + description +
                " (from: " + from + "to: " + to + ")");
    }

    /**
     * Reads a line in a .txt file that represents an Event task and converts it to an Event task.
     * @param segments A string array that is split by " | ", separating the line into useful chunks.
     * @return An Event task.
     */
    public static Event readFromFile(String[] segments) {
        String symbol = segments[1];
        String description = segments[2];
        String from = segments[3];
        String to = segments[4];
        Event toReturn =  new Event(description, from, to);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
    }

    /**
     * returns A String to be written into the .txt file.
     * @return A formatted String for the Deadline task with " | " separators.
     */
    @Override
    public String toWriteString() {
        String toWrite = "E" + " | " + super.toWriteString() + " | " + from + " | " + to;
        return toWrite;
    }
}
