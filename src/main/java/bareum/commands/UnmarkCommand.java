package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for marking a task from the task list as not completed.
 */
public class UnmarkCommand extends Command {
    /**
     * Index of task to mark as uncompleted.
     */
    private int index;

    /**
     * Create a new instance of a command that marks the task at the corresponding index as uncompleted when executed.
     * @param index Index of task to mark as uncompleted.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the corresponding index as uncompleted.
     * @param ui Lets the user know if the marking as uncompleted was successful.
     * @param storage Saves the updated task to the hard disk.
     * @param taskList Task list to retrieve the task from.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.unmarkAsDone(index);
        storage.saveAllTasks(taskList);

        Ui.reply("Okay, I've marked this task as not done yet:\n" + taskList.get(index).toString());
    }
}
