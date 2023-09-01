package data.tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public int findKeyword(String keyword) {
        return description.indexOf(keyword);
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s",
            isDone ? "X" : " ",
            description
        );
    }

    public String toFileFormatString() {
        return String.format(
            "%s|%s",
            isDone ? "1" : "0",
            description
        );
    }
}

