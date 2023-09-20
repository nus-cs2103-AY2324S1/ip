package mattbot.task;
/**
 * Implements a Todo style task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo Instance that is not done by default.
     */
    public Todo(String name) {
        super(name, false);
    }
    /**
     * Constructs a new Todo Instance that has no default done status.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }
    /**
     * Identifies itself as an Todo.
     *
     * @return Character to identify the type of Task.
     */
    public String identifier() {
        return "T";
    }

    /**
     * Returns String form for storage.
     *
     * @return String for storage format.
     */
    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName();
    }
}
