package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed that marks a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for MarkCommand.
     * @param fullCommand
     */
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the MarkCommand, marking a task as done.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.getCommand().substring(5)) - 1;
            taskList.mark(taskId);
            ui.showTaskMarked(taskList.get(taskId));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }
}
