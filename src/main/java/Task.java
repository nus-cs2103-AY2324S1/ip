public class Task {
    private String detail;
    private boolean done;

    public Task(String detail) {
        this.detail = detail;
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
            this.detail
        );
    }
}

