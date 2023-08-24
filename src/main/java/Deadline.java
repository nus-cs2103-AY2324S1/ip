public class Deadline extends Task {

    private final String by;

    /**
     * Constructs a Deadline with the specified name and due date.
     *
     * @param name The name of the deadline.
     * @param by   The due date of the deadline.
     */
    private Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Parses the command string to create a Deadline instance.
     *
     * @param input The command string.
     * @return A new Deadline instance.
     * @throws DukeException If the input format is invalid.
     */
    public static Deadline createFromCommandString(String input) throws DukeException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing '/by' or date for deadline.");
        }
        return new Deadline(parts[0], parts[1]);
    }

    public static Deadline fromFileFormat(String[] parts) {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        String by = parts[3].trim();
        Deadline deadline = new Deadline(name, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toFileFormat() {
        return "D|" + (isDone ? "1" : "0") + "|" + this.name + "|" + this.by + "|";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
