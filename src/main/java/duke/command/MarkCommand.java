package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents the command to mark tasks in the task list.
 */
public class MarkCommand extends Command {
    /** Index of the task to be marked. */
    private int index;

    /**
     * Constructor for the mark command.
     * 
     * @param index Index to be zero based to fit the Array List indexing.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to mark is invalid!");
        }
        taskList.markTask(index);
        ui.printMessage("Nice! I've marked this task as done:\n" + taskList.get(index));
        storage.saveList(taskList.getAllTasks());
    }
}
