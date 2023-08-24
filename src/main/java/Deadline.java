public class Deadline extends Task {

    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }
}