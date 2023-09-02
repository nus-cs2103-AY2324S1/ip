package duke;

/**
 * Represents a task that has to be done.
 */
public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String encode() {
        return String.format("T|%s", super.encode());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
