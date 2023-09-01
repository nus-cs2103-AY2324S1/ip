package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private Task taskToAdd;

    /**
     * Constructs an AddCommand object with the task to add.
     *
     * @param taskToAdd The task to be added.
     */
    public AddCommand(Task taskToAdd) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the add command, adding the task to the task list and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage handler for reading/writing tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskToAdd);
        storage.writeListToFile(taskList);
        taskList.printTaskListInString();
    }
}
