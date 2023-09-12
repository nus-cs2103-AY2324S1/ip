package chatbot.tasks;

public class Task {
    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }
    private String name;
    private boolean isDone;
    private Priority priority;

    public Task(String name) {
        this.name = name;
        this.priority = Priority.LOW;
    }

    public Task(String name, boolean isDone, Priority priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    public String getPriority() {
        switch (this.priority) {
        case HIGH:
            return "H";
        case MEDIUM:
            return "M";
        default:
            return "L";
        }
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.checkIsDone() ? "X" : " ",
                this.getName());
    }
}
