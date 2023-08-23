public class Task {
    private String description;
    private boolean done;

    Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
