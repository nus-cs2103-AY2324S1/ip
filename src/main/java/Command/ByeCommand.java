package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * ByeCommand is a subclass of Command.
 * Used to execute Bye Commands.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand
     */
    public ByeCommand() {}

    /**
     * method to execute the bye sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.byeMessage();
        storage.saveList(taskList);
    }

    /**
     * Method to decide whether program should continue
     * @return false, signifying end of program
     */
    @Override
    public boolean isContinue() {
        return false;
    }
}
