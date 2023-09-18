package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents a mark command, which will mark a task in the tasklist as completed.
 */
public class MarkCommand extends Command {
    private final int markTaskIndex;

    public MarkCommand(int i) {
        this.markTaskIndex = i;
    }


    /**
     * Executes the mark command marking a task in the current tasklist as completed.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this task as done:\n");
        Task markTask = taskList.getTaskToEdit(this.markTaskIndex);
        markTask.markDone();
        str.append(markTask + "\n");
        return str.toString();
    }
}
