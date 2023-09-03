package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task.
 */
public class MarkCommand extends Command {

    private int taskIndex;

    /**
     * Creates MarkCommand object with specified index of task to be deleted.
     *
     * @param taskIndex Index of task to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes mark command. Marks task in TaskList, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.mark(this.taskIndex);
        ui.showMark(tasks.getTask(this.taskIndex));
    }

    /**
     * Checks if the application should exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
