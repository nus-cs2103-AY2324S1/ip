public class Deadline extends Task {
    String end;
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.end + ")";
    }
}
