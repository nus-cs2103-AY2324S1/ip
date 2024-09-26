package ratspeak.task;

import ratspeak.exception.DukeException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents a deadline object.
 * it has an end date and time.
 */
public class Deadline extends Task {
    private final LocalDate deadlineDate;


    private static String parseDeadlineTaskName(String task) throws DukeException {
        String[] taskSplit = task.split("/by", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name\n");
        }

        return taskName;
    }

    private static LocalDate parseDeadline(String task) throws DukeException {
        String[] taskSplit = task.split("/by", 2);

        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid deadline (make sure to start /by)\n");
        }

        String dueDate = taskSplit[1].trim();
        if (dueDate.isEmpty()) {
            throw new DukeException("Please enter valid deadline: Do not leave it empty\n");
        }

        try {
            return LocalDate.parse(dueDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format: Use YYYY-MM-DD\n");
        }
    }

    /**
     * initialize Deadline object that models a deadline
     * @param task the deadline task name with a "/by" separating the description and the deadline
     * @throws DukeException If the description or deadline is empty or there is no "/by" or
     * the datetime format is wrong (not YYYY-MM-DD)
     */
    public Deadline(String task) throws DukeException {
        super(parseDeadlineTaskName(task), parseDeadline(task));
        this.deadlineDate = parseDeadline(task);

    }

    private Deadline(String task, boolean isDone, LocalDate deadlineDate) {
        super(task, deadlineDate, isDone);
        this.deadlineDate = deadlineDate;
    }

    /**
     * returns new Deadline object that is marked
     * @return Deadline object that is marked
     */
    @Override
    public Deadline done() {
        return new Deadline(super.getTask(), true, this.deadlineDate);
    }

    /**
     * returns new Deadline object that is unmarked
     * @return Deadline object that is unmarked
     */
    @Override
    public Deadline undone() {
        return new Deadline(super.getTask(), false, this.deadlineDate);
    }

    /**
     * returns format of string to be stored in hard disk
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
