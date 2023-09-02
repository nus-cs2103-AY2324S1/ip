package fishron;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Initializes a new DeleteCommand instance.
     *
     * @param taskNum The index of the task to delete.
     */
    public DeleteCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     *
     * @param taskList The task list to which the task will be deleted.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(taskNum);
        ui.showTaskDeleted(taskList, taskList.getTask(taskNum));
    }
}
