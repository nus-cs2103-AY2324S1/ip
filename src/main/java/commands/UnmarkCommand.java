package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed that unmarks a task.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor for UnmarkCommand.
     * @param fullCommand  The command to be executed
     */
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the UnmarkCommand, unmarking a task.
     * 
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(getFullCommand().substring(7)) - 1;
            taskList.unmark(taskId);
            ui.showTaskUnmarked(taskList.getTask(taskId));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }
}
