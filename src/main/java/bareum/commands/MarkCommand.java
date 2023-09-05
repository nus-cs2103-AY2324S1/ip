package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for marking a task from the task list as completed.
 */

public class MarkCommand extends Command {
    /**
     * Index of task to mark as completed.
     */
    private int index;

    /**
     * Create a new instance of a command that marks the task at the corresponding index as completed when executed.
     * @param index Index of task to mark as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the corresponding index as completed.
     * @param ui Lets the user know if the marking as completed was successful.
     * @param storage Saves the updated task to the hard disk.
     * @param taskList Task list to retrieve the task from.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.markAsDone(index);
        storage.saveAllTasks(taskList);

        Ui.reply("Well done! I've marked this task as done:\n" + taskList.get(index).toString());
    }
}
