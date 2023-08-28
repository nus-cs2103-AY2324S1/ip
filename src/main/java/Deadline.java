public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription()
                + "(by:" + this.deadline + ")";
    }

    @Override
    public String savedString() {
        return "D " + super.savedString() + " | " + this.deadline;
    }
}
