package duke;
public class Task {

    protected String details;
    protected boolean isCompleted;

    public Task(String details) {
        this.details = details;
        this.isCompleted = false;
    }

    public Task(String details, boolean isCompleted) {
        this.details = details;
        this.isCompleted = isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setIncomplete() {
        this.isCompleted = false;
    }

    protected String getDetails() {
        return this.details;
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
