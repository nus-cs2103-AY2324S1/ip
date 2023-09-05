package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for deleting a task from the task list.
 */

public class DeleteCommand extends Command {
    /**
     * Index of task to delete.
     */
    private int index;

    /**
     * Create a new instance of a command that deletes the task at the corresponding index when executed.
     * @param index Index of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete the task at the corresponding index.
     * @param ui Lets the user know if the deletion was successful.
     * @param storage Saves the remaining tasks to the hard disk.
     * @param taskList Task list to delete the task from.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String deletedTask = taskList.get(index).toString();
        taskList.delete(index);
        storage.saveAllTasks(taskList);
        Ui.reply("Okay, I've deleted this task from the list:\n" + deletedTask
                + "\nYou now have " + taskList.size() + " tasks in your list.");
    }
}
