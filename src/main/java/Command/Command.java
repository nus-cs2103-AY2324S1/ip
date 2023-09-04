package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * Abstract Command class
 */
public abstract class Command {

    /**
     * method to execute a command sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {}

    /**
     * Method to decide whether program should continue
     * @return true, signifying continuance of program
     */
    public boolean isContinue() {
        return true;
    }
}
