package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;

public class UndoCommand extends Command {
    private int stepsToUndo;

    public UndoCommand(int stepsToUndo) {
        this.stepsToUndo = stepsToUndo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        if (stepsToUndo > tasks.getMaxUndo() || stepsToUndo <= 0) {
            throw new DialogixException("Number of undo operations cannot exceed total number of operations performed "
                    + "and cannot be less than or equal to 0!");
        }

        tasks.undo(stepsToUndo);
        ui.printUndoSuccessMessage(stepsToUndo);
        storage.save(tasks.getAllTasks());
    }
}
