package duke;

public class Deadline extends Task {
    String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String encode() {
        return String.format("D|%s /by %s", super.encode(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
