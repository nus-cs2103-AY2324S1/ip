package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

/**
 * UnmarkCommand is a subclass of Command.
 * Used to execute Unmark Commands.
 */
public class UnmarkCommand extends Command {

    int index;
    /**
     * Constructor for UnmarkCommand
     * @param index index of task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * method to execute the unmark sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @return unmark message to be printed in the GUI
     * @throws DukeException organic exception for Duke
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append(taskList.unmark(this.index));
            storage.saveList(taskList);
        } catch (RuntimeException e) {
            throw new DukeException(String.format("Given index is out of range. Index range should be between" +
                            " 1 and %d.",
                    taskList.getSize()));
        }
        return String.valueOf(sb);
    }
}
