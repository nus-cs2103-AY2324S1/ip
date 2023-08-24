public class Deadline extends Task {

    protected String by;

    public Deadline(String description, int id, String by) {
        super(description, id);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return String.valueOf(this.id) + ". " + "[D]" + status + this.description + " (by: " + by + ")";
    }
}