package ekud.command;

public final class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return String.format("delete %d", taskId);
    }
}
