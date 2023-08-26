public class Deadline extends Task {
    protected String d_time;

    public Deadline(String description, String d_time) {
        super(description, TaskType.DEADLINE);
        this.d_time = d_time;
    }

    public Deadline(String description, String d_time, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.d_time = d_time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + d_time + ")";
    }
}