package bob.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bob.exception.InvalidPriorityException;
import bob.exception.MissingDatesException;

public class Deadline extends Task {

    private LocalDate due;

    /**
     * Deadline constructor
     * @param description in the form e.g."p/high submit task /by 2022-01-01"
     * @throws MissingDatesException
     */
    public Deadline(String description)
            throws MissingDatesException, InvalidPriorityException, IndexOutOfBoundsException {

        super(description.split(" /by ")[0].split(" ")[1]);

        try {
            this.due = LocalDate.parse(description.split(" /by ")[1]);
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

    public Deadline(String name, boolean done, String due, String priority) {
        super(name);
        super.done = done;
        super.priority = Enum.valueOf(Priority.class, priority);
        this.due = LocalDate.parse(due);
    }

    /**
     * Converts object to string representation for user display
     * @return string representation
     */
    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[D]" + done
                + super.priorityToString()
                + " " + this.name
                + " (by: " + this.due.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    /**
     * Parses string into a Deadline object
     * @param str is in the form e.g. "0 | high | read book | 2pm"
     * @return Deadline object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Deadline parseDeadline(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 4);

        boolean isDone = strSplit[0].equals("1");
        String priority = strSplit[1];
        String name = strSplit[2];
        String due = strSplit[3];

        return new Deadline(name, isDone, due, priority);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "deadline" + separation +
                (done ? 1 : 0) + separation
                + super.priority + separation
                + super.name + separation
                + due;
    }
}

