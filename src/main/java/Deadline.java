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
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "D|" + stat + "|" + this.description + "|" + this.end;
    }
}
