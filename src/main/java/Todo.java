public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String encodeTask() {
        return String.format("T;%s;%s", this.isDone ? "X" : " ", this.description);
    }
}
