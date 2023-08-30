package duke;

import duke.Command;
import duke.InvalidInputException;
import duke.Storage;
import duke.Ui;

public class UndoTask extends Command {
    private int taskToUndo;

    /**
     * Constructor for UndoTask command
     * @param taskToUndo the integer in the TaskList which we want to undo
     */
    public UndoTask(int taskToUndo) {
        this.taskToUndo = taskToUndo;
    }
    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        lst.get(taskToUndo).setUncompleted();
        ui.uncompletedMessage(taskToUndo, lst);
        try {
            storage.saveTasks(lst);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
