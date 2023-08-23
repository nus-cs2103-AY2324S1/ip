public abstract class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    public void markTaskCompleted(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String checkbox = "";
        if (this.done) {
            checkbox = "[X]";
        } else {
            checkbox = "[ ]";
        }
        return checkbox + " " + this.description;
    }
}
