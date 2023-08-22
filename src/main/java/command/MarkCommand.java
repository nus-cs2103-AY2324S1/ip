package command;

public final class MarkCommand extends Command {
    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
