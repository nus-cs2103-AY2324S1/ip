package duke;

public class Task {
    private String description;
    private boolean isMark;

    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    public String getStatusIcon() {
        return (isMark ? "X" : " "); // mark done task with X
    }

    public void setMark(boolean isMark) {
        this.isMark = isMark;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String writeToFile() {
        return this.description;
    }
}
