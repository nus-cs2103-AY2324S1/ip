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

    }
}
