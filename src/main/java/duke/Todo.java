package duke;

public class Todo extends Task {
    private static String parseTodo(String task) throws DukeException {
        String todoTask = task.trim();
        if (todoTask.isEmpty()) {
            throw new DukeException("Please enter valid deadline: Do not leave it empty");
        }

        return todoTask;
    }
    public Todo(String task) throws DukeException {
        super(parseTodo(task));
    }

    public Todo(String task, boolean isDone) {
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
    public String storageText() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
