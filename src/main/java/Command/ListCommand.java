package command;

import main.Storage;
import main.UI;
import task.TaskList;

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
     * @return list message to be printed in the GUI
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.printList(taskList.getTaskArrayList());
    }

}
