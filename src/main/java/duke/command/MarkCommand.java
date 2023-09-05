package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to mark tasks as completed in the task list.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Initialises a MarkCommand object.
     *
     * @param index The index in the task list to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given MarkCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to mark a task in.
     * @param ui The UI to print any output onto.
     * @param storage The storage to save and update tasks.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            tasks.markAsDone(index);
            ui.print("Nice! I've marked this task as done:");
            ui.print("  " + tasks.getTaskString(index));
            storage.saveTasks(tasks);
        } else {
            throw new DukeException("Cannot mark a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the MarkCommand.
     *
     * @return Mark.
     */
    @Override
    public String getCommandType() {
        return "Mark";
    }
}
