public class Task {
    private String title;
    private boolean done;

    public Task(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String indicator = done ? "X" : " ";
        return "[" + indicator + "] " + title;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
