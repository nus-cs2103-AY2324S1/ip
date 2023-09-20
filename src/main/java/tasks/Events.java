package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * .
 * The {@code Events} class represents a specific type of task called "event" in a task management application.
 * It is a child class of the {@code Task} class and inherits its properties and methods.
 * .
 * An event task is a task that starts at a specific date and time and ends at another specific date and time.
 * It includes a task description, a boolean flag indicating whether the task is done or not, and date/time information
 * specifying the event's start and end times.
 * .
 * This class provides a constructor to create an event task and overrides methods to customize its
 * string representation using {@code toString()} and data representation using {@code getData()}.
 */
public class Events extends Task {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final LocalDateTime from;
    protected final LocalDateTime to;

    /**
     * Public constructor to create a Event task
     *
     * @param description Task description
     * @param isDone      Boolean if task is done/marked
     * @param from        Datetime from of the event
     * @param to          Datetime to of the event
     */
    public Events(String description, Boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(dateTimeFormat)
                + " to: " + this.to.format(dateTimeFormat) + ")";
    }

    @Override
    public String getData() {
        String marked = isDone ? "1" : "0";
        return "E | " + marked + " | " + this.taskDesc + " | " + this.from.format(dateTimeFormat)
                + " | " + this.to.format(dateTimeFormat);
    }
}
