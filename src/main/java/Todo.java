public class Todo extends Task {
    Todo(String task) {
        super(task);
    }

    Todo(String task, boolean isDone) {
        super(task, isDone);
    }
    @Override
    public Todo done() {
        return new Todo(super.getTask(), true);
    }
    @Override
    public Todo undone() {
        return new Todo(super.getTask(), false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
