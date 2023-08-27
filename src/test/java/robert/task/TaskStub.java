package robert.task;

public class TaskStub extends Task {
    private final String description;
    private final boolean isDone;

    public TaskStub() {
        super("TASKSTUB", true);
        this.description = "TASKSTUB";
        this.isDone = true;
    }

    public String getDescription() {
        return "TASKSTUB";
    }

    public String getStatusIcon() {
        return "X"; // mark done task with X
    }

    @Override
    public String toString() {
        return "[X] " + this.description;
    }
}
