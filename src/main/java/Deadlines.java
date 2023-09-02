public class Deadlines extends Task {

    final String taskChar = "[D]";
    protected String dueDate;

    public Deadlines(String taskName, String deadline) {
        super(taskName);
        this.dueDate = deadline;
    }

    @Override
    public String toFileString() {
        String taskDetails = super.getTaskName() + " | " + this.dueDate;
        return "D | " + super.getStatusNumber() + " | " + taskDetails;
    }

    @Override
    public String toString() {
        return taskChar  + super.toString() + " (by: " + dueDate + ")";
    }
}
