package chatty;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String parse() {
        return "T | " + this.description + " | " + this.isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
