package duke;

/**
 * Represents an executable command for marking a task as not done in the task list by its index.
 */
public class TaskUnmarker implements Executable {
    private int index;
    private TaskList tasks;

    /**
     * Constructs a TaskUnmarker with the specified task list and index.
     *
     * @param tasks The task list in which the task will be marked as not done.
     * @param index The index of the task to be marked as not done.
     */
    public TaskUnmarker(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    /**
     * Marks the specified task as not done and returns a confirmation message.
     *
     * @return A message confirming the task has been marked as not done.
     */
    @Override
    public String execute() {
        tasks.unmark(index);
        Task task = tasks.get(index);
        return "OK, I've marked this task as not done yet:\n"
            +
            task.toString();
    }
}
