public class Deadline extends Task{
    private String by;
    public Deadline(String name, String by) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        }
        this.by = by;
    }

    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname() + ":" + this.by;
        } else {
            return "T : 0 : " + this.getname() + ":" + this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
