public class DeadlineTask extends Task {
    protected String by;
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public static DeadlineTask parseDeadline(String taskDetails) {
            String[] details = taskDetails.split("/by", 2);
            String description = details[0];
            String by = details[1];
        return new DeadlineTask(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
