public class DeadlineTask extends Task {
    protected String by;
    public DeadlineTask(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public static DeadlineTask parseDeadline(String taskDetails) throws RuntimeException {
        if (!taskDetails.contains("by:")) {
            throw new RuntimeException("Error! Remember to include 'by:' after the deadline command!");
        }
            String[] details = taskDetails.split("by:", 2);
            String description = details[0];
            String by = details[1];
        return new DeadlineTask(description, by);
    }

    public static DeadlineTask parseDeadline(String taskDetails, boolean isDone) throws RuntimeException {
        if (!taskDetails.contains("by:")) {
            throw new RuntimeException("Error! Remember to include 'by:' after the deadline command!");
        }
        String[] details = taskDetails.split("by:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String by = details[1];
        return new DeadlineTask(description, by, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
