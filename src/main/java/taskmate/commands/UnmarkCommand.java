package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;

/**
 * The UnmarkCommand class is a child class of the Command class. It represents the `unmark <unmarkIndex>` command which
 * marks tasks in the task list as incomplete.
 */
public class UnmarkCommand extends Command {
    String commandType;
    boolean isExit;
    int unmarkIndex;

    /**
     * UnmarkCommand constructor that allows the developer to specify the index of the task to be marked as incomplete.
     *
     * @param unmarkIndex the index of the task to be unmarked from the task list. This index is 1-based, which means
     * the unmarkIndex starts from 1 to the number of tasks available, rather than starting from 0.
     */
    public UnmarkCommand(int unmarkIndex) {
        this.commandType = "Unmark";
        this.isExit = false;
        this.unmarkIndex = unmarkIndex;
    }

    /**
     * Executes the `unmark` command by the user by attempting to retrieve the task to be unmarked from the task list
     * and unmarking it. If the task cannot be found (i.e. invalid unmarkIndex), a failure message is printed by the
     * `ui` instance to inform the user that the unmark command failed to be executed.
     * Upon successful execution, a success message is printed by the `ui` instance to inform the user about the
     * successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToUnmark = tasks.getTask(this.unmarkIndex);
            tasks.markAsNotDone(taskToUnmark);

            // print message when unmarking
            ui.printSuccessfulUnmarkResponse(taskToUnmark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
            return;
        }
    }
}
