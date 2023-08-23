public class Todo extends Task {
    Todo(String message) {
        super(message);
    }

    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.message;
    }
}
