package prts.command;

import prts.History;
import prts.OutOfRangeException;
import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * The command for undoing a past command.
 */
public class UndoCommand extends Command {
    private final Integer undoCount;

    public UndoCommand(Integer undoCount) {
        this.undoCount = undoCount;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws OutOfRangeException {
        return history.undo(undoCount, tasks);
    }

}
