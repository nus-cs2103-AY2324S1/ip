public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createDeadlineFromCommand(String command) {
        String[] split = command.substring(9).split(" /by ");
        return new Deadline(split[0], split[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

