package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

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
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        try {
            taskList.unmark(this.index);
            storage.saveList(taskList);
        } catch (RuntimeException e) {
            throw new DukeException(String.format("Given index is out of range. Index range should be between" +
                            " 1 and %d.",
                    taskList.size()));
        }
    }
}
