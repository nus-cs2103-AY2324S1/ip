public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createDeadlineFromCommand(String command) throws DukeException {

        if (command.length() <= 9) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!command.contains(" /by ")) {
            throw new DukeException("The deadline command must contain a /by.");
        } else if (command.endsWith(" /by ")) {
            throw new DukeException("The deadline command must contain a description after /by.");
        }

        String[] split = command.substring(9).split(" /by ");
        return new Deadline(split[0], split[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

