package command;

import exception.DukeException;
import main.Storage;
import main.UI;
import task.TaskList;

import static java.lang.Integer.parseInt;

/**
 * UpdateDescriptionCommand is a subclass of Command.
 * Used to execute Update Description Commands.
 */
public class UpdateDescriptionCommand extends Command {

    private String updatedDescription;
    private int taskIndex;

    /**
     * Constructor for UpdateDescriptionCommand
     * @param command user command
     */
    public UpdateDescriptionCommand(String command) {
        String shortCommand = command.substring(18).trim();
        this.taskIndex = parseInt(shortCommand, 0, 1, 10);

        String[] list = shortCommand.split("/");

        this.updatedDescription = list[1].trim().substring(7);

    }

    /**
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @return execute message to be printed in the GUI
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String message = taskList.updateTaskDescription(taskIndex, this.updatedDescription);
        storage.saveList(taskList);
        return message;
    }
}
