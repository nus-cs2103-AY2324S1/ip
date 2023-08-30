package chatbot.tasks;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.checkIsDone() ? "X" : " ",
                this.getName());
    }
}
