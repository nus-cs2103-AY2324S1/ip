package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents a Delete command, which will delete a task from the tasklist.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int i) {
        this.indexToDelete = i;
    }

    /**
     * Executes the delete command, deleting a task in the current tasklist.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:\n");
        Task deletedTask = taskList.getTask(this.indexToDelete);
        str.append("  " + deletedTask + "\n");
        taskList.removeTask(deletedTask);
        str.append("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
        return str.toString();
    }
}
