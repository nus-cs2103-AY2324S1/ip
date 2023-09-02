package bob.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bob.exception.MissingDatesException;

public class Deadline extends Task {

    private LocalDate due;

    /**
     * Deadline constructor
     * @param description in the form e.g."submit task /by 2022-01-01"
     * @throws MissingDatesException
     */
    public Deadline(String description) throws MissingDatesException {
        super(description.split(" /by ")[0]);

        try {
            this.due = LocalDate.parse(description.split(" /by ")[1]);
        } catch (Exception e) {
            throw new MissingDatesException();
        }
    }

    public Deadline(String name, boolean done, String due) {
        super(name);
        super.done = done;
        this.due = LocalDate.parse(due);
    }

    /**
     * Converts object to string representation for user display
     * @return string representation
     */
    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[D]" + done + " " + this.name
                + " (by: " + this.due.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    /**
     * Parses string into a Deadline object
     * @param str is in the form e.g. "0 | read book | 2pm"
     * @return Deadline object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Deadline parseDeadline(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 3);

        boolean isDone = strSplit[0].equals("1");
        String name = strSplit[1];
        String due = strSplit[2];

        return new Deadline(name, isDone, due);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "deadline" + separation + (done ? 1 : 0) + separation
                + super.name + separation + due;
    }
}

