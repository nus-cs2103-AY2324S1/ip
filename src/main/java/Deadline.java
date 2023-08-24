public class Deadline extends Task{
    private String by;
    public Deadline(String name, String by) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
