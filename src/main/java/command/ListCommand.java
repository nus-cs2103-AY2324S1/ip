package command;

import exception.DukeException;
import task.TaskList;
import main.Storage;
import main.UI;


/**
 * ListCommand is a subclass of Command.
 * Used to execute List Commands.
 */
public class ListCommand extends Command {

    /**
     * Constructor for List Command
     */
    public ListCommand() {
    }

    /**
     * method to execute the list sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printList(taskList.getTaskArrayList());
    }

}
