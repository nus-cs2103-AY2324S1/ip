public class Task {
    private final String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String mark = isDone ? "x" : " ";
        return "[" + mark + "] " + title;
    }
}
