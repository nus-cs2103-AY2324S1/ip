public class Deadline extends Task {
    protected String d_time;

    public Deadline(String description, String d_time) {
        super(description);
        this.d_time = d_time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + d_time + ")";
    }
}