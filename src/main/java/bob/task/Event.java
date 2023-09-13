package bob.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidPriorityException;
import bob.exception.MissingDatesException;

public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    /**
     * Event constructor
     * @param description in the form e.g."p/mid travelling /from 2022-01-01 /to 2023-01-01"
     * @throws MissingDatesException
     * @throws DateTimeParseException
     */
    public Event(String description)
            throws MissingDatesException, InvalidPriorityException, IndexOutOfBoundsException {

        super(description.split(" /from ")[0].split(" ")[1]);

        try {
            this.start = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[0]);
            this.end = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[1]);
        } catch (Exception e) {
            throw new MissingDatesException();
        }

        try {
            String priority = description.split(" /by ")[0].split(" ")[0].split("/")[1];
            super.priority = Enum.valueOf(Priority.class, priority);
        } catch (Exception e) {
            throw new InvalidPriorityException();
        }
    }

    public Event(String name, boolean done, String start, String end, String priority) {
        super(name);
        super.done = done;
        super.priority = Enum.valueOf(Priority.class, priority);
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
        return "[E]" + done + " "
                + super.priorityToString()
                + this.name
                + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Parses string into an Event object
     * @param str is in the form e.g. "0 | high | read book | 2pm | 4pm"
     * @return Event object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Event parseEvent(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 5);

        boolean isDone = strSplit[0].equals("1");
        String priority = strSplit[1];
        String name = strSplit[2];
        String start = strSplit[3];
        String end = strSplit[4];

        return new Event(name, isDone, start, end, priority);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "event" + separation
                + (done ? 1 : 0) + separation
                + super.priority + separation
                + super.name + separation
                + start + separation + end;
    }
}
