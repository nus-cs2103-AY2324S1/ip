package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An extension of <code>Task</code>, a <code>Event</code> object keeps track of
 * a task that has a start and end time (both either a LocalDate or just a String)
 */

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected boolean areBothDates = true;

    /**
     * The class constructor.
     *
     * @param description Description of the Event task.
     * @param from Start of the event - either a LocalDate or String
     * @param to End of the event - either a LocalDate or String
     */

    public Event(String description, String from, String to) {
        super(description);

        //To treat the event start and end as dates,
        //the user must input them as either of the following formats.
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            this.fromDate = LocalDate.parse(from, formatter1);
        } catch (DateTimeParseException eDate) {
            try {
                this.fromDate = LocalDate.parse(from, formatter2);
            } catch (DateTimeParseException eDate2) {
                areBothDates = false;
                this.from = from;
                this.to = to;
            }
        }

        //if only one is a date, there's no point in treating either like a date
        if (areBothDates) {
            try {
                this.toDate = LocalDate.parse(to, formatter1);
            } catch (DateTimeParseException eDate) {
                try {
                    this.toDate = LocalDate.parse(to, formatter2);
                } catch (DateTimeParseException eDate2) {
                    areBothDates = false;
                    this.from = from;
                    this.to = to;
                }
            }
        }
    }

    /**
     * Gets the value of from, whether a <code>LocalDate</code> or <code>String</code>
     */
    public String getFrom() {
        if (!areBothDates) {
            return this.from;
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fromDate.format(formatter1);
    }

    /**
     * Gets the value of to, whether a <code>LocalDate</code> or <code>String</code>
     */
    public String getTo() {
        if (!areBothDates) {
            return this.to;
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return toDate.format(formatter1);
    }

    /**
     * Returns the string form of the <code>Event</code> task, for writing to file.
     */
    @Override
    public String storedString() {
        return "E | " + super.storedString() + " | " + getFrom() + " | " + getTo();
    }

    /**
     * Returns the string form of the <code>Event</code> task, for display to user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
