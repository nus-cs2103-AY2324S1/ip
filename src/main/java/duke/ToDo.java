package duke;

/**
 * The ToDo class represents a task.
 * It extends the Task class and provides methods to mark and unmark the task as done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDos instance with the specified task description.
     *
     * @param task The description of the task.
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return "[T]" + checkbox + task;
    }
}
