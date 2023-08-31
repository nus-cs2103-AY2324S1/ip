package helpbuddy.command;

import helpbuddy.storage.Storage;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

/**
 * The AddCommand class adds a value of type Task to TaskList and calls Ui to print corresponding
 * message upon execution.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a new AddCommand object with the specified Task.
     * @param task the task to be added by AddCommand object.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a Task into TaskList and calls Ui to print corresponding message.
     * @param taskList the tasklist for Task to be added to.
     * @param ui the ui that prints message.
     * @param storage the storage with saved data in TaskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        ui.printAddTaskMessage(this.task, taskList);
    }

    /**
     * Compares the object to the specified object. The result is true if and only if argument is not null and
     * the Task added is the same Task as the object.
     * @param obj the object to compare with.
     * @return true if objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof AddCommand) {
            AddCommand addCmd = (AddCommand) obj;
            return this.task.equals(addCmd.task);
        }

        return false;
    }
}
