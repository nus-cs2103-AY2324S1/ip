package ekud.command;

public final class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return String.format("unmark %d", taskId);
    }
}
