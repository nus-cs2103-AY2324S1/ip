package duke;

public class Task {
    private final String desc;
    private boolean marked;

    public Task(String desc) {
        this.desc = desc;
        marked = false;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", marked ? "X" : " ", desc);
    }
}
