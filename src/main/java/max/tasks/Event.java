package max.tasks;

import java.time.LocalDate;

/**
 * Represents event object.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    /**
     * Initialises event object with a description, start date, end date and done status.
     *
     * @param item Description of task
     * @param fromDate Start date
     * @param toDate End date
     */
    public Event(String item, LocalDate fromDate, LocalDate toDate) {
        super(item);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    /**
     * Initialises event object with a description, start date, end date and done status.
     * Used when loading task from storage.
     *
     * @param item Description of task
     * @param fromDate Start date
     * @param toDate End date
     * @param done Status
     */
    public Event(String item, LocalDate fromDate, LocalDate toDate, boolean done) {
        super(item, done);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    /**
     * Returns string representation of Event task in the app.
     *
     * @return String string representation of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.toString() + " to: " + toDate.toString() + ")";
    }
    /**
     * Returns string representation of Event task in saved file.
     *
     * @return String string representation of Event task
     */
    @Override
    public String saveItem() {
        return "E | " + super.saveItem()
                + " from: " + fromDate.toString() + " to: " + toDate.toString() + "\n";
    }
}
