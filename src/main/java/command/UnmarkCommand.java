package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents an unmark command, which will mark a task in the tasklist as not completed.
 */
public class UnmarkCommand extends Command {
    private final int unmarkTaskIndex;
    public UnmarkCommand(int i) {
        this.unmarkTaskIndex = i;
    }

    /**
     * Executes the unmark command marking a task in the current tasklist as not completed.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("OK, I've marked this task as not done yet:\n");
        Task unmarkTask = taskList.getTaskToEdit(this.unmarkTaskIndex);
        unmarkTask.markUndone();
        str.append(unmarkTask + "\n");
        return str.toString();
    }
}
