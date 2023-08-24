public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by") - 1));
        int byIndex = description.indexOf("/by");
        this.deadline = description.substring(byIndex + 4);
    }
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
