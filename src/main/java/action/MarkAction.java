package action;

public final class MarkAction extends Action {
    private final int taskId;

    public MarkAction(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return String.format("mark %d", taskId);
    }
}
