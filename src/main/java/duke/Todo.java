package duke;

import java.time.LocalDate;

public class Todo extends Task {

    private static final String TYPE = "[T]";

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
