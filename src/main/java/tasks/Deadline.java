package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * .
 * The {@code Deadline} class represents a specific type of task called "deadline" in a task management application.
 * It is a child class of the {@code Task} class and inherits its properties and methods.
 * .
 * A deadline task is a task that needs to be completed before a specific date and time. It includes a task description,
 * a boolean flag indicating whether the task is done or not, and date/time information specifying the task's deadline.
 * .
 * This class provides a constructor to create a deadline task and overrides methods to customize its
 * string representation using {@code toString()} and data representation using {@code getData()}.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
