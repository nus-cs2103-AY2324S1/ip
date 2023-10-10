package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand to save tasks to storage and display the exit message.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        return ui.showExit();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true since ExitCommand is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
