public class Deadline extends Task {

    private String by;

    private Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline createFromCommandString(String input) throws DukeException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing '/by' or date for deadline.");
        }
        return new Deadline(parts[0], parts[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
