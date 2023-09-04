package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * DeadlineCommand is a subclass of Command.
 * Used to execute Deadline Commands.
 */
public class DeadlineCommand extends Command {

    String fullCommand;

    /**
     * Constructor for DeadlineCommand
     * @param fullCommand full user input
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * method to execute the deadline sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addDeadline(fullCommand);
        storage.saveList(taskList);
    }
}
