package task;

public class Deadline extends Task {
    private static final String TASK_HEADER = "[D] ";
    private final String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return Deadline.TASK_HEADER + super.toString() + "(by: " + this.time + ")";
    }
}
