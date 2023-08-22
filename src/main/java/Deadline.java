/**
 * A task which holds the date which should be completed by.
 */
public class Deadline extends Task {

    /**
     * The due date of the deadline task.
     */
    private String date;

    /**
     * Constructs an unmarked Deadline task
     * with the given description and due date.
     *
     * @param description The description of the task.
     * @param date The due date of the task.
     */
    public Deadline(String description, String date) throws DukeException{
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline task.
     * Includes its completion status, description, and due date.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return isDone ? "[D][X] "+this.description + " (by: "+this.date+")"
                : "[D][ ] "+this.description + " (by: "+this.date+")";
    }
}
