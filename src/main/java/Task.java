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
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s",
            this.isDone ? "X" : " ", 
            this.description
        );
    }

    public String toFileFormatString() {
        return String.format(
            "%s|%s",
            this.isDone ? "1" : "0",
            this.description
        );
    }
}

