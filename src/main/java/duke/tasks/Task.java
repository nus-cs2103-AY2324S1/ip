package duke.tasks;

public abstract class Task {
    public String description;
    public boolean isDone;

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
