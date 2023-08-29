package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the Duke chatbot.
 * Inherits from the Command class.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a goodbye message and saving the tasks to storage.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage component.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbyeMessage();
        storage.save(tasks.getTasks());
    }

    /**
     * Returns a boolean indicating whether the command is an exit command.
     *
     * @return A boolean indicating whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
