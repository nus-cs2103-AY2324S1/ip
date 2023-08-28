public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s",
            this.done ? "X" : " ", 
            this.description
        );
    }

    public String toFileFormatString() {
        return String.format(
            "%s|%s",
            this.done ? "1" : "0",
            this.description
        );
    }
}

