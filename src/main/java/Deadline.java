public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String name, String dueBy) {
        super(name);
        this.dueBy = dueBy;
    }

    public String toPrint() {
        String str = "[D] " + super.getStatus() + " " + super.name + " (by: " + this.dueBy + ")";
        return str;
    }
}
