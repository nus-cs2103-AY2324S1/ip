package duke;

public class Task {
    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        OTHERS
    }
    protected String description;
    protected boolean isDone;
    protected Type taskType;

    public Task(String description, Type taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {

        this.isDone = true;
    }

    public void markAsNotDone() {

        this.isDone = false;
    }
}
