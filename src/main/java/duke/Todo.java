package duke;

import java.time.LocalDate;

/**
 * The object that represents a todo task.
 */
public class Todo extends Task {

    private static final String TYPE = "[T]";

    /**
     * Creates a todo object.
     *
     * @param task Task description.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    @Override
    public String toSaveFormat() {
        return "Todo | " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return Todo.TYPE + super.toString();
    }
}
