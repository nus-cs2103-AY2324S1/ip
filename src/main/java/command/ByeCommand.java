package command;

import exception.DukeException;
import task.TaskList;
import main.UI;
import main.Storage;


/**
 * ByeCommand is a subclass of Command.
 * Used to execute Bye Commands.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand
     */
    public ByeCommand() {
    }

    /**
     * method to execute the bye sequence
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui       user interface responsible for terminal messages
     * @param storage  object to communicate with data.txt file
     * @return bye message to be printed in the GUI
     * @throws DukeException organic exception for Duke
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        storage.saveList(taskList);
        return ui.printByeMessage();
    }

    /**
     * Method to decide whether program should continue
     *
     * @return false, signifying end of program
     */
    @Override
    public boolean isContinue() {
        return false;
    }
}
