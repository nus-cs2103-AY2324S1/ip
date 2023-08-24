public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[T][ ] " + this.getName();
        } else {
            return "[T][X] " + this.getName();
        }
    }
}
