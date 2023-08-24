public class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        isCompleted = false;
    }

    public String getDetails() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    public void markCompleted() {
        isCompleted = true;
    }

    public void unmarkCompleted() {
        isCompleted = false;
    }
}
