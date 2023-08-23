public class Deadline extends Task {
    String byDate;
    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + this.description + "(by:" + byDate + ")";
    }
}
