package Tasks;

/**
 * Represents a task with a deadline in the Duke application.
 * This class extends the base class Task and includes the due date of the deadline.
 */
public class Deadline extends Task {

    private String dueDate;

    /**
     * Constructs a new Deadline object with a title and due date.
     *
     * @param title    The title of the deadline task.
     * @param dueDate  The due date of the deadline.
     */
    public Deadline(String title, String dueDate, boolean isMarked) {
        super(title, isMarked);
        this.dueDate = dueDate;
    }

    /**
     * Generates a string representation of the Deadline object.
     *
     * @return A formatted string indicating the status, title, and due date of the deadline.
     */
    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[D]" + mark + title + " (by: " + this.dueDate + ")";
    }

    @Override
    public String toSave() {
        String res = "D";
        res += (isMarked ? "| 1" : "| 0");
        res += "| " + title;
        res += "| " + dueDate;
        return res;
    }
}

