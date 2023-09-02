package duke;

/**
 * Represents an executable command for marking a task as done in the task list by its index.
 */
public class TaskMarker implements Executable {
    private int index;
    private TaskList tasks;

    /**
     * Constructs a TaskMarker with the specified task list and index.
     *
     * @param tasks The task list in which the task will be marked as done.
     * @param index The index of the task to be marked as done.
     */
    public TaskMarker(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    /**
     * Marks the specified task as done and returns a confirmation message.
     *
     * @return A message confirming the task has been marked as done.
     */
    @Override
    public String execute() {
        tasks.mark(index);
        Task task = tasks.get(index);
        return "Nice! I've marked this task as done:\n"
            + task.toString();
    }
}
