import java.util.StringJoiner;

public class Task {
    private final String taskName;
    private boolean isComplete = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void markIncomplete() {
        this.isComplete = false;
    }

    private String getStatus() {
        return isComplete ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return getStatus() + this.taskName;
    }

    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add(this.isComplete ? "1" : "0").add(this.taskName);
        return joiner.toString();
    }
}
