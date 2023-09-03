package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Task;

/**
 * The DeleteCommand class is a child class of the Command class. It represents the `delete <deleteIndex>` command which
 * deletes tasks from the task list.
 */
public class DeleteCommand extends Command {
    String commandType;
    boolean isExit;
    int deleteIndex;

    /**
     * DeleteCommand constructor that allows the developer to specify the index of the task to be removed.
     *
     * @param deleteIndex the index of the task to be removed from the task list. This index is 1-based, which means
     * the deleteIndex starts from 1 to the number of tasks available, rather than starting from 0.
     */
    public DeleteCommand(int deleteIndex) {
        this.commandType = "Delete";
        this.isExit = false;
        this.deleteIndex = deleteIndex;
    }

    /**
     * Executes the `delete` command by the user by attempting to retrieve the task to be deleted from the task list and
     * removing it. If the task cannot be found (i.e. invalid deleteIndex), a failure message is printed by the `ui`
     * instance to inform the user that the delete command failed to be executed.
     * Upon successful execution, a success message is printed by the `ui` instance to inform the user about the
     * successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToMark = tasks.getTask(this.deleteIndex);
            tasks.removeTask(this.deleteIndex);

            // print message when deleting
            ui.printSuccessfulDeleteResponse(taskToMark, tasks.getNumTotalTasks());
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
            return;
        }

    }
}
