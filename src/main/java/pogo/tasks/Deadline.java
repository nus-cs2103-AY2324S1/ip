package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /**
     * Deadline of the task.
     */
    protected String by;

    public Deadline(String description, String by) throws PogoInvalidTaskException {
        super(description);

        if (by.equals("")) {
            throw new PogoInvalidTaskException("Deadline cannot be empty");
        }
        this.by = by;
    }

    @Override
    public String getStatusMessage() {
        return "[D]" + super.getStatusMessage() + " (by: " + this.by + ")";
    }


    /**
     * Returns the deadline of the task.
     * @return Deadline of the task.
     */
    public String getDeadline() {
        return this.by;
    }

    /**
     * Accepts a visitor that performs an action on the task.
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    public static Deadline fromFormattedString(String input) throws PogoInvalidTaskException {
        String[] split = input.split(" \\| ");
        if (split.length != 4) {
            throw new PogoInvalidTaskException();
        }

        if (!split[0].equals("D")) {
            throw new PogoInvalidTaskException();
        }

        if (!split[1].equals("1") && !split[1].equals("0")) {
            throw new PogoInvalidTaskException();
        }

        boolean isDone = split[1].equals("1");

        Deadline deadline = new Deadline(split[2], split[3]);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }
}
