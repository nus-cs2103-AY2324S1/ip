package duke.task;

import duke.exception.EmptyDescriptionException;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * Initialize a new Event from user input.
     *
     * @param input User input.
     * @return A new Event object.
     * @throws EmptyDescriptionException If user input does not follow the given format.
     */
    public static Event initializeFromInput(String input) throws EmptyDescriptionException {
        try {
            String splitInput = input.split("event")[1];
            String description = splitInput.split("/from")[0].strip();
            String from = splitInput.split("/from")[1].split("/to")[0].strip();
            String to = splitInput.split("/to")[1].strip();
            return new Event(description, from, to);
        } catch (Exception e) {
            throw new EmptyDescriptionException("event", "event project meeting /from Mon 2pm /to 4pm");
        }
    }

    /**
     * Initialize a new Event from file storage.
     *
     * @param input Line from file storage.
     * @return A new Event object.
     */
    public static Event initializeFromStorage(String input) {
        String[] processed = input.split("\\(");
        String taskName = processed[0].trim();
        String from = processed[1].split("from:")[1].split("to:")[0].trim();
        String to = processed[1].split("to:")[1].split("\\)")[0].trim();
        return new Event(taskName, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
