package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

/**
 * DeadlineCommand is a subclass of Command.
 * Used to execute Deadline Commands.
 */
public class DeadlineCommand extends Command {

    private String fullCommand;

    /**
     * Constructor for DeadlineCommand
     * @param fullCommand full user input
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * method to execute the deadline sequence
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui       user interface responsible for terminal messages
     * @param storage  object to communicate with data.txt file
     * @return deadline message to be printed in the GUI
     * @throws DukeException organic exception for Duke
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String message = taskList.addDeadline(fullCommand);
        storage.saveList(taskList);
        return message;
    }
}
