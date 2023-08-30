public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String name, String dueBy, String isDone) {
        super(name, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + dueBy;
    }

    @Override
    public String toString() {
        String str = "[D] " + super.getStatus() + " " + super.name + " (by: " + this.dueBy + ")";
        return str;
    }
}
