package tasks;

public class Task {
    private String content;
    private boolean marked;

    public Task(String content) {
        this.content = content;
        this.marked = false;
    }

    public Task(String content, boolean status) {
        this.content = content;
        this.marked = status;
    }

    public Task mark() {
        return new Task(content, true);
    }

    public Task unmark() {
        return new Task(content);
    }

    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "added: " + this.content + "\n" +
                "____________________________________________________________";
    }

    public String getContent() {
        return this.content;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String toString() {
        if (!this.marked) {
            return String.format("[ ] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }
}
