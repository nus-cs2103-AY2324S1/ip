package bob.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exception.MissingDatesException;

public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    /**
     * Event constructor
     * @param description in the form e.g."travelling /from 2022-01-01 /to 2023-01-01"
     * @throws MissingDatesException
     * @throws DateTimeParseException
     */
    public Event(String description) throws MissingDatesException, DateTimeParseException {
        super(description.split(" /from ")[0]);

        try {
            this.start = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[0]);
            this.end = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[1]);
        } catch (Exception e) {
            throw new MissingDatesException();
        }
    }

    public Event(String name, boolean done, String start, String end) {
        super(name);
        super.done = done;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Converts object to string representation for user display
     * @return string representation
     */
    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[E]" + done + " " + this.name
                + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Parses string into an Event object
     * @param str is in the form e.g. "0 | read book | 2pm | 4pm"
     * @return Event object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Event parseEvent(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 4);

        boolean isDone = strSplit[0].equals("1");
        String name = strSplit[1];
        String start = strSplit[2];
        String end = strSplit[3];

        return new Event(name, isDone, start, end);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "event" + separation + (done ? 1 : 0) + separation
                + super.name + separation + start + separation + end;
    }
}
