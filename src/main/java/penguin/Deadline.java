package penguin;

public class Deadline extends Task {
    String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getDisplay() {
        return "[D]" + super.getDisplay() + "by " + this.by;
    }
}
