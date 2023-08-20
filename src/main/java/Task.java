public class Task {

    protected String details;
    protected boolean isCompleted;

    public Task(String details) {
        this.details = details;
        this.isCompleted = false;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.details;
        } else {
            return "[ ] " + this.details;
        }
    }
}
