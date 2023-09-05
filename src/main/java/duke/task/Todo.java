package duke.task;


import duke.exception.DukeException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private static String parseTodo(String task) throws DukeException {
        String todoTask = task.trim();
        if (todoTask.isEmpty()) {
            throw new DukeException("Please enter valid deadline: Do not leave it empty");
        }

        return todoTask;
    }

    /**
     * Initialize Todo object that models a todo
     * @param task todo description
     * @throws DukeException if description is empty
     */
    public Todo(String task) throws DukeException {
        super(parseTodo(task));
    }

    private Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns new Todo object that is marked
     * @return Todo object that is marked
     */
    @Override
    public Todo done() {
        return new Todo(super.getTask(), true);
    }

    /**
     * Returns new Todo object that is unmarked
     * @return Todo object that is unmarked
     */
    @Override
    public Todo undone() {
        return new Todo(super.getTask(), false);
    }

    /**
     * Returns format of string to be stored in hard disk
     * @return string
     */
    @Override
    public String storageText() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
