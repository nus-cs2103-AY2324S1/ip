package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Represents a generic task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) throws PogoInvalidTaskException {
        super(description);
    }

    @Override
    public String getStatusMessage() {
        return "[T]" + super.getStatusMessage();
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
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
