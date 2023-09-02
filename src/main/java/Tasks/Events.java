package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of Tasks to create tasks that start at a specific date/time and ends at a specific date/time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019.
 */
public class Events extends Task {

    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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