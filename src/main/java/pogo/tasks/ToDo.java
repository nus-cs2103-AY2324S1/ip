package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    @Override
    public String getStatusMessage() {
        return "[T]" + super.getStatusMessage();
    }

    @Override
    public String toFormattedString() {
        return String.format("T | %s", super.toFormattedString());
    }

    public static ToDo fromFormattedString(String input) throws PogoInvalidTaskException {
        String[] split = input.split(" \\| ");
        if (split.length != 3) {
            throw new PogoInvalidTaskException();
        }

        if (!split[0].equals("T")) {
            throw new PogoInvalidTaskException();
        }

        if (!split[1].equals("1") && !split[1].equals("0")) {
            throw new PogoInvalidTaskException();
        }

        boolean isDone = split[1].equals("1");

        ToDo todo = new ToDo(split[2]);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }
}
