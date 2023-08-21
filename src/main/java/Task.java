public class Task {
    protected final String description;
    protected final Boolean marked;

    Task(String description, Boolean marked) {
        this.description = description;
        this.marked = marked;
    }

    Task(String description) {
        this.description = description;
        this.marked = false;
    }

    Task mark() {
        return new Task(this.description, true);
    }

    Task unmark() {
        return new Task(this.description, false);
    }
    @Override
    public String toString() {
        return "[" +  (this.marked ? "X" : " ") + "] " + this.description;
    }
}
