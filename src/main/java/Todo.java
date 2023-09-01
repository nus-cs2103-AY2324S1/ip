public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String taskToStringStore(Task task) {
        return "T-" + super.taskToStringStore(task);
    }
}
