public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        return String.join("|", "todo", done, this.description);
    }
}
