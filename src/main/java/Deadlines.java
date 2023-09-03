public class Deadlines extends Task {
    protected String by;
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String toSave() {
        return String.format("D|%s|%s|%s", this.getStatusIcon(), this.getDescription(), this.by);
    }
}
