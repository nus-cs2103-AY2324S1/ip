package dude.command;

import java.io.IOException;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

/**
 * Represents a command that marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as undone.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        try {
            output = "Executing Unmark Command\n";
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            output = output + ui.showUnmarkedTask(unmarkedTask);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            output = "Error in Unmark Command";
        }
        return output;
    }
}
