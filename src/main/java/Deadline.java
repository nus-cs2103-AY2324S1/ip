public class Deadline extends Task {
    private final String DEADLINE;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getDEADLINE() {
        return this.DEADLINE;
    }

    public Deadline(String deadlineDesc, boolean isDone, String deadline) {
        super(deadlineDesc, isDone);
        this.DEADLINE = deadline;
        this.taskType = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "by: " + this.DEADLINE;
    }
}
