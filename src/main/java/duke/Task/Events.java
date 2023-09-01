package duke.Task;

import duke.DukeException.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class for events
 */
public class Events extends Task {
    /**
     * For the start
     */
    private LocalDate start;
    /**
     * For the end
     */
    private LocalDate end;

    /**
     * The constructor
     * @param name the name of the event task
     * @param start The starting time
     * @param end The ending time
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

    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "E,0," + this.getName() + "," + this.start + "," + this.end + "\n";
        } else  {
            return "E,1," + this.getName() + "," + this.start + "," + this.end + "\n";
        }
    }

    /**
     * To convert the task to string
     * @return a string
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
     * To check whether the input is a Duke.Task.Deadlines
     * @param input the task
     * @return Boolean
     * @throws DukeException
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