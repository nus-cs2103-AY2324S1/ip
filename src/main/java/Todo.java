public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public static Todo createNewTodoTask(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
