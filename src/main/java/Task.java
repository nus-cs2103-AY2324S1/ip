public class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }
}
