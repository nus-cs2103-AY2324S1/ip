public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String task, int taskId, String deadline) {
        super(task, taskId);
        this.deadline = deadline;
    }

    @Override
    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String submitDate = "(by: " + deadline.substring(3) + ")";
        return taskId + "." + "[D]" + checkbox + task + " " + submitDate;
    }
    @Override
    public String checkBox() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String submitDate = "(by: " + deadline.substring(3) + ")";
        return "[D]" + checkbox + task + " " + submitDate;
    }
}
