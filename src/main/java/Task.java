public class Task {
    protected TaskType type;
    protected String description;
    protected boolean isDone;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getFileString() {
        return (isDone ? "✅" : "⭕️") + "|" + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }
}
