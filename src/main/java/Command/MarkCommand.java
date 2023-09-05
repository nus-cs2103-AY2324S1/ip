package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

/**
 * Mark Command is a subclass of Command.
 * Used to execute Mark Commands.
 */
public class MarkCommand extends Command {

    int index;

    /**
     * Constructor for MarkCommand
     * @param index index of task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * method to execute the mark sequence
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @throws DukeException organic exception for Duke
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        try {
            taskList.mark(this.index);
            storage.saveList(taskList);
        } catch (RuntimeException e) {
            throw new DukeException(String.format("Given index is out of range. Index range should be between" +
                            " 1 and %d.",
                    taskList.size()));
        }
    }
}
