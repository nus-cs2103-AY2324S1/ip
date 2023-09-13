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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Unmark Command");;
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            ui.showUnmarkedTask(unmarkedTask);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Unmark Command");
        }
    }
}
