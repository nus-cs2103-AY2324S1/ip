public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toSaveDataFormat() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(), getBy());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getBy());
    }
}