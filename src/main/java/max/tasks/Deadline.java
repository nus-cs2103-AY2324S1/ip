package max.tasks;

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

    /**
     * Returns string representation of Deadline task in the app.
     *
     * @return String string representation of Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")";
    }
    /**
     * Returns string representation of Deadline task in the saved file.
     *
     * @return String string representation of Deadline task
     */
    @Override
    public String saveItem() {
        return "D | " + super.saveItem() + "by: " + byDate.toString() + "\n";
    }
}
