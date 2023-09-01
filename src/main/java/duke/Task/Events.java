package duke.Task;

import duke.DukeException.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent task belong to events.
 */
public class Events extends Task {
    /**
     * For the start.
     */
    private LocalDate start;
    /**
     * For the end.
     */
    private LocalDate end;

    /**
     * Create the event task.
     * @param name Description of the event.
     * @param start Starting time of the event.
     * @param end Ending time of the event.
     */
    public Events (String name, String start, String end) throws DukeException {
        super(name);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new DukeException(" OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }

    /**
     * Create the string to be saved in storage.
     * @return String that will be saved in storage.
     */
    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "E,0," + this.getName() + "," + this.start + "," + this.end + "\n";
        } else  {
            return "E,1," + this.getName() + "," + this.start + "," + this.end + "\n";
        }
    }

    /**
     * Convert the task to a string.
     * @return String that represent the event.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Check whether the input is a valid event.
     * @param input Task that will be checked.
     * @return Boolean that represent whether the input is a event.
     * @throws DukeException Exception where the event is not valid.
     */
    public static boolean isEvent(String input) throws DukeException {
        if(input.split( " ")[0].equals("event")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS! The description of event cannot be empty");
            } else if (!input.contains("/from")){
                throw new DukeException("OOPS! The description of event does not contain /from");
            } else if (!input.contains("/to")){
                throw new DukeException("OOPS! The description of event does not contain /to");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}