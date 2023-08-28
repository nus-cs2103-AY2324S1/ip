class Deadline extends Task {
    final String by;
    public Deadline(boolean done, String desc) throws DukeException {
        super(done, desc.substring(9, desc.indexOf("/by")));
        this.by = desc.substring(desc.indexOf("/by") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}