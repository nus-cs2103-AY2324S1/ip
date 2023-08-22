enum TaskType {
    TODO, DEADLINE, EVENT
}
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

//    public String getDescription() {
//        return this.description;
//    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //...
}