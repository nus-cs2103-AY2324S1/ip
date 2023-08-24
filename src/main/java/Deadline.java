public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by")));
        int byIndex = description.indexOf("/by");
        this.deadline = description.substring(byIndex + 3);
    }
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.description + "(by:" + this.deadline + ")";
    }
}
