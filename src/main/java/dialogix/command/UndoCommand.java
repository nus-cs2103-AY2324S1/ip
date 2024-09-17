package dialogix.command;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;

/**
 * Represents a command to undo a specified number of previous operations in the task list.
 */
public class UndoCommand extends Command {
    private final int steps;

    /**
     * Constructs an UndoCommand with the number of steps to undo.
     *
     * @param stepsToUndo The number of steps to undo.
     */
    public UndoCommand(int stepsToUndo) {
        this.steps = stepsToUndo;
    }

    /**
     * Executes the UndoCommand by undoing the specified number of previous operations in the task list
     * and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        isUndoValid(tasks.getMaxUndo());

        tasks.undo(steps);
        ui.printUndoSuccessMessage(steps);
        storage.save(tasks.getAllTasks());
    }

    /**
     * Validates whether undoing a specified number of steps is valid based on the maximum undo limit.
     *
     * @param size The maximum number of steps that can be undone.
     * @throws DialogixException If the number of steps is out of bounds.
     */
    private void isUndoValid(int size) throws DialogixException {
        if (steps > size || steps <= 0) {
            throw new DialogixException("Number of undo operations cannot exceed the total number "
                    + "of operations performed and cannot be less than or equal to 0!");
        }
    }
}
