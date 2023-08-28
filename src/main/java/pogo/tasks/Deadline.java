package pogo.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusMessage() {
        return "[D]" + super.getStatusMessage() + " (by: " + this.by + ")";
    }

    @Override
    public String toFormattedString() {
        return String.format("D | %s | %s", super.toFormattedString(), this.by);
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
