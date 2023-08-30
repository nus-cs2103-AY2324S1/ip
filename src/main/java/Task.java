import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String storeText() {
        return " ";
    }

    public void markedChecker(String string) {
        if (string.equals("false")) {
            return;
        } else {
            this.markAsDone();
        }
    }


    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}
