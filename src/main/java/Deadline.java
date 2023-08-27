public class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + this.description + " (by: " + byDate + ")";
    }


    @Override
    public String fileString() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + byDate;
    }
}
