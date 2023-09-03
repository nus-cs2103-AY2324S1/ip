package devybot.tasks;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public boolean checkDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + description;
    }

    public String toFileString() {
        String status = isDone ? "1" : "0";
        return status + " | " + description;
    }

}
