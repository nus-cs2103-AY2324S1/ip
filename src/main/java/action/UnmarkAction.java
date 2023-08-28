package action;

public final class UnmarkAction extends Action {
    private final int taskId;

    public UnmarkAction(int taskId) {
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
