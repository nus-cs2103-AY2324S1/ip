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
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui       user interface responsible for terminal messages
     * @param storage  object to communicate with data.txt file
     * @return delete message to be printed in the GUI
     * @throws DukeException organic exception for Duke
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String message = ui.showDelete(fullCommand, taskList);
        taskList.deleteTask(fullCommand);
        storage.saveList(taskList);
        return message;
    }

}
