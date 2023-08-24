public class Deadline extends Task {
    protected String dueDatetime;

    public Deadline(String description, String dueDatetime) {
        super(description);
        this.dueDatetime = dueDatetime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDatetime);
    }
}
