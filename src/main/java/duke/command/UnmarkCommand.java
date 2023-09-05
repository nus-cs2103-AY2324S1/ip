package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to mark tasks as not completed in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Initialises an UnmarkCommand object.
     *
     * @param index The index in the task list to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given UnmarkCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to unmark a task in.
     * @param ui The UI to print any output onto.
     * @param storage The storage to save and update tasks.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            tasks.unmarkAsDone(index);
            ui.showLine();
            ui.print("OK, I've marked this task as not done yet:");
            ui.print("  " + tasks.getTaskString(index));
            ui.showLine();
            storage.saveTasks(tasks);
        } else {
            throw new DukeException("Cannot unmark a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the UnmarkCommand.
     *
     * @return Unmark.
     */
    @Override
    public String getCommandType() {
        return "Unmark";
    }
}
