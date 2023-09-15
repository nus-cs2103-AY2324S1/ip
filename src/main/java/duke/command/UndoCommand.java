package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to undo the most recent user action in the task list.
 * This command reverses actions such as adding, deleting, marking, or unmarking tasks.
 */
public class UndoCommand extends Command {
    private boolean isExit = false;

    /**
     * Executes the 'Undo' command to revert the most recent user action.
     *
     * @param tasks   The list of tasks to which the task will be added or from which it will be removed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String recentCommand = ui.retrieveRecentCommand();
        if (recentCommand == "") {
            throw new DukeException("OOPS!!! There's no commands for me to undo!");
        }

        switch (recentCommand) {
        case "add":
            DeleteCommand delete = new DeleteCommand(tasks.size() - 1);
            delete.execute(tasks, ui, storage);
            break;
        case "delete":
            Task recentlyDeleted = ui.retrieveDeletedTask();

            tasks.addTask(recentlyDeleted);
            storage.updateFile(tasks);
            break;
        case "mark":
            UnmarkCommand unmark = new UnmarkCommand(ui.retrieveLatestMarked());
            unmark.execute(tasks, ui, storage);
            break;
        case "unmark":
            MarkCommand mark = new MarkCommand(ui.retrieveLatestUnmarked());
            mark.execute(tasks, ui, storage);
            break;
        default:
            assert false : "Nothing to undo here!";
        }

        ui.updateRecentCommand("");

        //printing messages
        String message = "Got it. Undo for the " + recentCommand
                + " command was successful!";
        ui.updateMessage(message);
    }

    /**
     * Checks if the 'Undo' command should trigger the program to exit.
     *
     * @return false since it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
