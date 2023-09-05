package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

/**
 * DeleteCommand is a subclass of Command.
 * Used to execute Delete Commands.
 */
public class DeleteCommand extends Command {
    private String fullCommand;

    /**
     * Constructor for DeleteCommand
     * @param fullCommand full user input
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * method to execute the delete sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.deleteTask(fullCommand);
        storage.saveList(taskList);
    }

}
