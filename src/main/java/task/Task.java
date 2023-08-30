package task;

public class Task {
    protected String description;
    protected boolean isDone;
    public static int tasksCounter = 0;
    private int index;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.index = tasksCounter + 1;
        tasksCounter++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String output = String.format("%d. [%s] %s", index, getStatusIcon(), description);
        return output;
    }
}
