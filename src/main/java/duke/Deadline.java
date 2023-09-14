package duke;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s) %s", super.toString(), this.by, super.getTags());
    }

    @Override
    public String toTaskListEntry() {
        return String.format("D | %d | %s | %s | %s",
                this.getIsDone() ? 1 : 0, this.getName(), this.by, super.getTags());
    }
}
