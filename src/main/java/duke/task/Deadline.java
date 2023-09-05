package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline object.
 * It has an end date and time.
 */
public class Deadline extends Task {
    private final LocalDate deadlineDate;


    private static String[] parseDeadline(String task) throws DukeException {
        String [] taskSplit = task.split("/by", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name");
        }

        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid deadline (make sure to start /by)");
        }

        String dueDate = taskSplit[1].trim();
        if (dueDate.isEmpty()) {
            throw new DukeException("Please enter valid deadline: Do not leave it empty");
        }

        return new String[] {taskSplit[0], dueDate};
    }

    /**
     * Initialize Deadline object that models a deadline
     * @param task the deadline task name with a "/by" separating the description and the deadline
     * @throws DukeException If the description or deadline is empty or there is no "/by" or
     * the datetime format is wrong (not YYYY-MM-DD)
     */
    public Deadline(String task) throws DukeException {
        super(parseDeadline(task)[0]);
        try {
            this.deadlineDate = LocalDate.parse(parseDeadline(task)[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format: Use YYYY-MM-DD\n");
        }

    }

    private Deadline(String task, boolean isDone, LocalDate deadlineDate) {
        super(task, isDone);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns new Deadline object that is marked
     * @return Deadline object that is marked
     */
    @Override
    public Deadline done() {
        return new Deadline(super.getTask(), true, this.deadlineDate);
    }

    /**
     * Returns new Deadline object that is unmarked
     * @return Deadline object that is unmarked
     */
    @Override
    public Deadline undone() {
        return new Deadline(super.getTask(), false, this.deadlineDate);
    }

    /**
     * Returns format of string to be stored in hard disk
     * @return string
     */
    @Override
    public String storageText() {
        String end = deadlineDate.toString();
        return "[D]" + super.toString() + "/by " + end;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
