package main.java.command;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;
/**
 * Represents an undo Command, which will undo the latest action.
 */
public class UndoCommand extends Command {

    /**
     * Executes the undo command, undoing the latest command.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.revertPreviousTaskList();
        return "Task has been undone!";
    }
}
