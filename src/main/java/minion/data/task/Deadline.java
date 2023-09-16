package minion.data.task;

import java.time.LocalDateTime;

import minion.parser.DatetimeParser;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime datetime;
    private String datetimeString;

    /**
     * Creates a deadline object. This is the main constructor of the minion.data.task.Deadline class.
     * @param description Description of deadline.
     * @param isDone Whether the deadline is done.
     * @param datetime Datetime of deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime datetime) {
        super(description, isDone);
        this.datetime = datetime;
        this.datetimeString = DatetimeParser.convertFromDatetime(datetime);
        taskSymbol = TaskSymbol.DEADLINE.getSymbol();
    }

    /**
     * Creates a deadline object. This calls the main constructor when the default for isDone is false.
     * @param description Description of deadline.
     * @param datetime Datetime of deadline.
     */
    public Deadline(String description, LocalDateTime datetime) {
        this(description, false, datetime);
    }

    /**
     * Returns whether the deadline contains the query.
     * @param query the query.
     * @return whether the deadline contains the query.
     */
    @Override
    public boolean contains(String query) {
        return description.contains(query) || datetimeString.contains(query);
    }

    /**
     * Returns the string representation of the deadline task.
     * @return string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + taskSymbol + "]" + super.toString() + " (by: " + datetimeString + ")";
    }

    /**
     * Returns a string representation of the deadline to be used in storage.
     * @return string representation of the deadline for storage.
     */
    @Override
    public String toStringStorage() {
        return taskSymbol + " | " + (isDone ? "1" : "0") + " | " + description + " | " + datetimeString;
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Deadline)) {
            return false;
        }
        Deadline t = (Deadline) o;
        return description.equals(t.description) && isDone == t.isDone && datetimeString.equals(t.datetimeString);
    }

    /**
     * Compares two deadline objects based on their datetimes
     * @param o the other deadline object
     * @return an integer denoting the relative order of this task and the other task
     */
    public int compare(Deadline o) {
        return datetime.compareTo(o.datetime);
    }
}
