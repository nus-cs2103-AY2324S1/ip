package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand.
     * @param fullCommand
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the DeleteCommand, deleting the task from the list of tasks.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(getFullCommand().substring(7)) - 1;
            assert getFullCommand().substring(0, 6).equals("delete") : "delete not in command";
            ui.showTaskDeleted(taskList.getTask(taskId), taskList.size());
            taskList.delete(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }
}
