public class Deadline extends Task{

    private static String TYPE = "[D]";
    protected String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.TYPE + super.toString() + " (by: " + this.deadline + ")";
    }
}
