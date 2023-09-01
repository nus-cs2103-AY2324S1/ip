package max.tasks;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

import java.time.LocalDate;

/**
 * Represents event object.
 */
public class Event extends Task {
    private LocalDate fromDate, toDate;
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
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.toString() + " to: " + toDate.toString() +")";
    }
    @Override
    public String saveItem() {
        return "E | " + super.saveItem() +
                " from: " + fromDate.toString() + " to: " + toDate.toString() + "\n";
    }
}