package duke.tasks;

/**
 * The duke.tasks.Todo class containing
 * tasks with no time limits.
 * @author: Shishir
 **/
public class Todo extends Task {

    /** The constructor.
     * @param description The description of the task.
     **/
    public Todo(String description) {
        super(description);
    }

    /** The constructor.
     * @param description The description of the task.
     * @param status The status of completion.
     **/
    public Todo(String description, String status) {
        super(description, status);
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }

    public String toFile() {
        return "T" + super.toFile();
    }

}
