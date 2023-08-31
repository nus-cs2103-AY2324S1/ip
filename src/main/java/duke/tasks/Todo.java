package duke.tasks;

/**
 * A subclass of Task. Contains a description and isDone.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param description The name of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for the Task class.
     *
     * @param description The name of the Task.
     * @param isDone If task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of the Todo, along with the
     * indication of whether it isDone.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String to be written into the .txt file for saving
     * of the file.
     *
     * @return String to be written into the save file.
     */
    @Override
    public String write() {
        return "T | " + super.write();
    }
}