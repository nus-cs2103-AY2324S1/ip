package duke.command;

import java.util.Stack;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to undo the previous user command.
 * This command is responsible for reversing the effects of the most recent user command.
 */
public class UndoCommand implements Command {
    private static Stack<Command> previousCommands = new Stack<>();

    /**
     * Saves a command to the stack of previous commands.
     *
     * @param command The command to be saved for potential undo.
     */
    public static void saveCommand(Command command) {
        if (command instanceof UndoCommand) {
            //Do nothing
        } else {
            previousCommands.push(command);
        }
    }

    /**
     * Executes the undo command, reversing the effects of the most recent user command.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @throws DukeException If an error occurs during the undo process.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (UndoCommand.previousCommands.size() == 0) {
            ui.sendMessage("Error 404!!! There is no command to be undone.");
        } else {
            ui.sendMessage("Undoing the previous command...");
            UndoCommand.previousCommands.pop().undoTask(tasks).execute(tasks, ui, storage);
            //ui.sendMessage("Undo Command is executed successfully.");
        }
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new EmptyCommand();
    }
}
