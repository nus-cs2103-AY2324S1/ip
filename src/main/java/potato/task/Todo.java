package potato.task;

import potato.*;

public class Todo extends Task {
    private static final String LINE = "-----------------------------------------\n";
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo todoParse(String input, String mark) {
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            assert input.length() > 1 : "input length should be > 1";
            return new Todo(input, (mark.equals("1")));
        }
    }

    @Override
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
