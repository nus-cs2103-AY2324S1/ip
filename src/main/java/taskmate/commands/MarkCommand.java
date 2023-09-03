package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;

/**
 * The MarkCommand class is a child class of the Command class. It represents the `mark <markIndex>` command which
 * marks tasks in the task list as completed.
 */
public class MarkCommand extends Command {
    String commandType;
    boolean isExit;
    int markIndex;

    /**
     * MarkCommand constructor that allows the developer to specify the index of the task to be marked as completed.
     *
     * @param markIndex the index of the task to be marked from the task list. This index is 1-based, which means
     * the markIndex starts from 1 to the number of tasks available, rather than starting from 0.
     */
    public MarkCommand(int markIndex) {
        this.commandType = "Mark";
        this.isExit = false;
        this.markIndex = markIndex;
    }

    /**
     * Executes the `mark` command by the user by attempting to retrieve the task to be marked from the task list and
     * marking it. If the task cannot be found (i.e. invalid markIndex), a failure message is printed by the `ui`
     * instance to inform the user that the mark command failed to be executed.
     * Upon successful execution, a success message is printed by the `ui` instance to inform the user about the
     * successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToMark = tasks.getTask(this.markIndex);
            tasks.markAsDone(taskToMark);

            // print message when marking
            ui.printSuccessfulMarkResponse(taskToMark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
        }

    }
}
