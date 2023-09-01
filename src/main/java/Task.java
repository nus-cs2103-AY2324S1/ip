public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String type();

    public abstract String toFileString();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void updateTaskStatus(boolean expectedStatus, String doneMessage, String undoneMessage) {
        if (isDone == expectedStatus) {
            System.out.println(doneMessage);
        } else {
            isDone = expectedStatus;
            System.out.println(undoneMessage);
        }
    }
}
