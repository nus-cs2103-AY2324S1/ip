public class Todo extends Task {


    public Todo(String description) {

        super(description, TaskType.TODO);
    }

    public Todo(String name, boolean isDone) {
        super(name, TaskType.TODO, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
