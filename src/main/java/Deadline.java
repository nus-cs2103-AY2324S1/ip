public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), this.by);
    }

    @Override
    public String encodeTask() {
        return String.format("D;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.by);
    }
}
