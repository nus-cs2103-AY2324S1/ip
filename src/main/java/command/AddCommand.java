package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to add a task to the taskList.
 * This class is a child of the abstract Command class and contains method to execute the add task command,
 * including methods to check whether it is an exit command.
 */
public class AddCommand extends Command {
    // The task to be added.
    Task task;

    /**
     * Creates a new add command with the specified task to be added.
     *
     * @param task The task to be added to the taskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     * This method adds the task to the taskList, and invokes the UI to display the add task message.
     *
     * @param tasks The list of task on which the command will operate on.
     * @param ui The UI which is used during the command execution to show add text.
     * @param storage The storage where tasks are stored and retrieved from, currently not utilized in this method but can be extended to use in the future.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAddText(task, tasks.size());
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
