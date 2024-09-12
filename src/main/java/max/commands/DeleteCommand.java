package max.commands;

import max.exception.InvalidArgumentException;
import max.exception.MaxException;
import max.storage.Storage;
import max.tasks.Task;
import max.tasks.TaskList;
import max.ui.Ui;

/**
 * Represents delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNumber;

    /**
     * Specifies index of task number to be deleted.
     *
     * @param taskNumber Index of task number to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    /**
     * Executes delete command. Deletes task from task list, updates storage, prints success message.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        try {
            Task deleted = tasks.getList().get(taskNumber - 1);
            tasks.delete(taskNumber);
            storage.writeToFile(tasks);
            return ui.showDelete(deleted, tasks.getList().size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please ensure the number is within the index range of your list!");
        }
    }
    /**
     * Checks if command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
