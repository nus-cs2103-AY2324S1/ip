package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents command Bye which exits the Chat Bot.
 *
 * @author Armando Jovan Kusuma
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @throws DukeException If the command execution fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        storage.writeToFile(tasks);
    }

}
