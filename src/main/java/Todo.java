public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public static Todo createNewTodoTask(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
