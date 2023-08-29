package duke;

public class Task {

    protected Tag tag;
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
    public String getStatusIcon() {
        return (isCompleted? "X" : " ");
    }

    public void markAsComplete() {
        this.isCompleted = true;
    }
    public void markAsIncomplete() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        return description;
    }
}
