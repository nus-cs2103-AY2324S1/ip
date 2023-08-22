package command;

public final class UnmarkCommand extends Command {
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
