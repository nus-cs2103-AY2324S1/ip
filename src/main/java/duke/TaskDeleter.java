package duke;

/**
 * Deletes a task from a TaskList.
 */
public class TaskDeleter extends Command {

    //The index for the task to be deleted.
    private int index;

    //The tasklist used to delete tasks.
    private TaskList taskList;

    /**
     * Instantiates an instance of TaskDeleter
     * @param index The index of the task to be deleted
     * @param taskList The tasklist used
     */
    public TaskDeleter(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    /**
     * Deletes specified task from the tasklist
     * @return The confirmation of the deleted task.
     */
    @Override
    public String execute() {
        return taskList.delete(index);
    }
}
