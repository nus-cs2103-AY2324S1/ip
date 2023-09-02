package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
/**
 * Represents the Command Delete that delete a task from the list
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class DeleteCommand extends Command {
    private final Integer taskIndex;

    /**
     * Constructs a DeleteCommand with a specified description of a task.
     *
     * @param taskIndex the task index that will be deleted.
     */
    public DeleteCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     * @throws DukeException If there are an invalid Input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.length()) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        Task taskTemp = tasks.getTask(taskIndex);
        tasks.delete(taskIndex);
        ui.printDelete(taskTemp, tasks);
    }

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
