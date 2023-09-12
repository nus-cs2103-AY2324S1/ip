package seedu.duke.task;


/**
 * Represents a Todo task.
 * This class is inherited from the Task class.
 * A Deadline task always have a description.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Todo extends Task {

    /**
     * The main constructor of the Todo task.
     *
     * @param description Description of this Todo task.
     */
    public Todo(String description) {
        super(description);
        assert !super.isDone : "Task should be marked as undone initially.";
    }

    /**
     * Returns a string representation of this
     * Todo task when it is saved in the
     * storage.
     *
     * @return a string representation of this Todo task for saving.
     */
    public String toStringForSave() {
        return "T" + " | " + super.toStringForSave();
    }

    /**
     * {@inheritDoc}
     *
     * Returns a string representation of this
     * Todo task when it is printed.
     *
     * @return a string representation of this Todo task for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
