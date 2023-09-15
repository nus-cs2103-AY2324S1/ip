package dialogix.command;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DoneCommand with the index (1-based) of the task to be marked as done.
     *
     * @param taskIndex The index (1-based) of the task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DoneCommand by marking a task as done in the task list and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        isDoneValid(tasks.size());
        tasks.addToStack();
        tasks.markTaskAsDone(taskIndex);
        ui.printMessage("Nice! I've marked this task as done:\n\t\t" + tasks.get(taskIndex));
        storage.save(tasks.getAllTasks());
    }

    /**
     * Validates whether marking a task as done is valid based on the task list size.
     *
     * @param size The size of the task list.
     * @throws DialogixException If the index is out of bounds.
     */
    private void isDoneValid(int size) throws DialogixException {
        if (taskIndex < 0 || taskIndex >= size) {
            throw new DialogixException("OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
    }
}
