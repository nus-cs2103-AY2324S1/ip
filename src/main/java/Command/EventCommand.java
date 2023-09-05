package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * EventCommand is a subclass of Command.
 * Used to execute Event Commands.
 */
public class EventCommand extends Command {
    String fullCommand;

    /**
     * Constructor for EventCommand
     * @param fullCommand full user input
     */
    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * method to execute the event sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addEvent(fullCommand);
        storage.saveList(taskList);
    }
}
