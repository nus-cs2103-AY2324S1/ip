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
 * Represents deadline object.
 */
public class Deadline extends Task {
    private LocalDate byDate;
    /**
     * Initialises deadline object with a description, by date and done status.
     *
     * @param item Description of task
     * @param byDate Due date
     */
    public Deadline(String item, LocalDate byDate) {
        super(item);
        this.byDate = byDate;
    }
    /**
     * Initialises deadline object with a description, by date and done status.
     * Used when loading task from storage.
     *
     * @param item Description of task
     * @param byDate Due date
     * @param done Status
     */
    public Deadline(String item, LocalDate byDate, boolean done) {
        super(item, done);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")";
    }
    @Override
    public String saveItem() {
        return "D | " + super.saveItem() + "by: " + byDate.toString() + "\n";
    }
}