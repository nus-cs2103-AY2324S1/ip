package duke;

/**
 * Represents an executable command for deleting a task from the task list by its index.
 */
public class TaskDeleter implements Executable {
    private int index;
    private TaskList tasks;

    /**
     * Constructs a TaskDeleter with the specified task list and index.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param index The index of the task to be deleted.
     */
    public TaskDeleter(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    /**
     * Deletes the task from the task list and returns a confirmation message.
     *
     * @return A message confirming the deletion of the task and the updated task list size.
     */
    @Override
    public String execute() {
        Task task = tasks.get(index);
        tasks.delete(index);
        return String.format("Noted. I've removed this task:\n"
                +
                "%s\n"
                +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
