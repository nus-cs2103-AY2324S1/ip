package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of Tasks to create tasks that need to be done before a specific date/time.
 * e.g., submit report by 2023-04-12 15:00.
 */
public class Deadline extends Task {

    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime by;

    /**
     * Public constructor to create a deadline task
     *
     * @param description Task description
     * @param isDone      Boolean if task is done/marked
     * @param by          Datetime of deadline
     */
    public Deadline(String description, Boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(dateTimeFormat) + ")";
    }

    @Override
    public String getData() {
        String marked = isDone ? "1" : "0";
        return "D | " + marked + " | " + this.taskDesc + " | " + this.by.format(dateTimeFormat);
    }
}