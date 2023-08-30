/**
 * Deadline is a task with a deadline date.
 */
public class Deadline extends Task{
    /**
     * Deadline date
     */
    protected String by;

    /**
     * Constructs a new Deadline object
     * @param name Name of deadline
     * @param by Date of deadline
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Return string representation of Deadline
     * @return String representation of Deadline
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String generateSaveString() {
        return String.format("D | %b | %s /by %s", isDone, name, by);
    }
}
