package Command;

import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * ListCommand is a subclass of Command.
 * Used to execute List Commands.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     */
    public ListCommand() {}

    /**
     * method to execute the list sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.list(taskList.getTaskArrayList());
    }

}
