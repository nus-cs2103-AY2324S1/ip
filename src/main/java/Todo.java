public class Todo extends Task{

    private Todo(String title) {
        super(title);
    }

    /**
     * Adds a new To-do task to the list of tasks.
     * @param title Title of task.
     * @return Task object created.
     */
    public static Task addTodo(String title) {
        Task todo = new Todo(title);
        taskList.add(todo);
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
