package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.errors.TaskError;

/**
 * Command to undo the latest command.
 * Only can be executed once for each.
 */
public class UndoCommand extends Command {

    private final Undoable potentialCommandToRollback;

    public UndoCommand(Undoable cmd) {
        potentialCommandToRollback = cmd;
    }

    /**
     * Undoes potentialCommandToRollback.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        if (potentialCommandToRollback == null) {
            throw new DotException("No command to undo, or you have already undone.",
                    TaskError.ERR_NO_UNDO_FOUND);
        }
        handleDotOutput.accept("Undoing your latest command...");
        potentialCommandToRollback.undo(handleDotOutput);
    }

    @Override
    public boolean isUndoCommand() {
        return true;
    }

}
