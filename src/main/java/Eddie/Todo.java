package Eddie;

/**
 * Represents a task to be done no date specified.
 */
public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public String getType() {
        return "T";
    }
}
