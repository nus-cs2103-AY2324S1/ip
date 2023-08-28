package Task;

public class Todo extends Task {
    private static final String TASK_HEADER = "[T] ";
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Todo.TASK_HEADER + super.toString();
    }
}
