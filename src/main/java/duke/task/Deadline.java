package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a deadline inherits from a Task.
 */
public class Deadline extends Task {
    private static String invalidDate = "Please provide date with the following format: YYYY-MM-DD";
    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     * @param name
     * @param deadline
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline with specified isDone.
     * @param name
     * @param isDone
     * @param deadline
     */
    public Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Checks if a deadline is on specified String date
     * @param dateStr date in String format
     * @return boolean whether a deadline is on specified String date
     * @throws DukeException if invalid date
     */
    public boolean isToday(String dateStr) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return this.deadline.compareTo(date) == 0;
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
            this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("deadline,>%s,>%s", super.exportToText(), this.deadline);
    }
}
