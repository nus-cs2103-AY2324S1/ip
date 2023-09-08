package bot.utils.tasks;

import bot.exceptions.InvalidTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event subclass. Contains a name, event start time and end time.
 */
class Event extends Task {
    /**
     * Event start time.
     */
    private final LocalDate from;
    /**
     * Event end time.
     */
    private final LocalDate to;

    /**
     * Default constructor.
     *
     * @param name Event name.
     * @param from Event start time.
     * @param to   Event end time.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Alternative constructor. Usually used when reading data from a file.
     *
     * @param name   Name of task.
     * @param isDone Completion status of task.
     * @param from   Start time of task.
     * @param to     End time of task.
     */
    protected Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Reads a string of standardised data and constructs an Event object based on the information.
     *
     * @param str Data string.
     * @return Event object.
     * @throws InvalidTaskException If an Event object cannot be created.
     */
    public static bot.utils.tasks.Event convertFromDataString(String str) throws InvalidTaskException {
        if (!str.matches("e/[01]/.+/.+/.+")) {
            throw new InvalidTaskException("Could not read Event.");
        }
        String[] arr = str.split("/");
        return new bot.utils.tasks.Event(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
    }

    /**
     * Creates an Event object.
     *
     * @param str Raw string to create the object from.
     * @return Event object.
     * @throws InvalidTaskException If the input string cannot create the Event object.
     */
    public static bot.utils.tasks.Event makeEvent(String str) throws InvalidTaskException {
        String[] comps = str.split("/");
        if (comps.length != 3) {
            throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                    + "event ... /from ... /to ...");
        } else if (comps[0].trim().equals("event")) {
            throw new InvalidTaskException("Sorry, the event description can't be empty.");
        } else if (comps[1].trim().equals("from")) {
            throw new InvalidTaskException("Sorry, event start time can't be empty.");
        } else if (comps[2].trim().equals("to")) {
            throw new InvalidTaskException("Sorry, event end time can't be empty.");
        } else if (!comps[1].startsWith("from ") || !comps[2].startsWith("to ")) {
            throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                    + "event ... /from ... /to ...");
        }
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(comps[1].substring(5).trim());
            to = LocalDate.parse(comps[2].substring(3).trim());
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("One or more dates are invalid.");
        }
        if (from.isAfter(to)) {
            throw new InvalidTaskException("Event end time can't be before event start time!");
        }
        return new bot.utils.tasks.Event(comps[0].substring(6).trim(), from, to);
    }

    /**
     * Get event start time.
     *
     * @return Event start time.
     */
    protected LocalDate getFrom() {
        return this.from;
    }

    /**
     * Get event end time.
     *
     * @return Event end time.
     */
    protected LocalDate getTo() {
        return this.to;
    }

    /**
     * String representation of the event.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.getFrom().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.getTo().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns data string representation.
     *
     * @return Data string.
     */
    public String convertToDataString() {
        return "e/" + (super.isDone() ? "1" : "0") + "/" + super.getName()
                + "/" + this.getFrom() + "/" + this.getTo();
    }

    /**
     * Checks for sameness. Events are the same if they have the same name, start time
     * and end time.
     *
     * @param o Object to compare to.
     * @return True if objects are the same, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof bot.utils.tasks.Event) {
            return this.getName().equals(((bot.utils.tasks.Event) o).getName())
                    && this.getFrom().equals(((bot.utils.tasks.Event) o).getFrom())
                    && this.getTo().equals(((bot.utils.tasks.Event) o).getTo());
        }
        return false;
    }
}
