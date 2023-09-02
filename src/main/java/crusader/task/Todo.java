package crusader.task;

/**
 * A todo task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s",super.toString());
    }

    @Override
    public String toFormat() {
        return String.format("T|%s|%s", super.getName(), super.isDone() ? "X" : " ");
    }
}
