public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String toFormat() {
        return String.format("D|%s|%s|%s", super.getName(), super.isDone() ? "X" : " ", this.by);
    }
}
