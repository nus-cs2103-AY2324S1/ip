package action;

public final class DeleteAction extends Action {
    private final int taskId;

    public DeleteAction(int taskId) {
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
