public class Deadline extends Task {

    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }
}
