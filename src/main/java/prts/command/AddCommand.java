package prts.command;

import prts.SaveToFileException;
import prts.Storage;
import prts.TaskList;
import prts.Ui;
import prts.task.Task;

/**
 * The general command used for adding a Task to the TaskList.
 * This Task can be any of the Task types.
 */
public class AddCommand extends Command {

    private Task taskToAdd;

    /**
     * Constructs the AddCommand object with the Task to be added to the list.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the addition of the Task to the TaskList.
     *
     * @param tasks   The list of tasks currently stored.
     * @param ui      The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @return The string to be displayed to the user upon successful execution.
     * @throws SaveToFileException If the Storage object fails to save the state of the TaskList
     *                             after the addition of the Task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException {
        String ret = tasks.add(taskToAdd);
        storage.save(tasks);
        return ret;
    }
}
