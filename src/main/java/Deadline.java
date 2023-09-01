public class Deadline extends Task{

    private String TaskIcon = "[D]";
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s (by: %s)", this.TaskIcon,this.getStatusIcon(), this.getDescription(), this.deadline);
        return message;
    }
}
