package duke.command;


import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.messages.ErrorMessages;
import duke.task.Task;


/**
 * A command that unmarks a specified task
 */

public class UnmarkCommand extends Command {
    protected int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Unmarks the specific task provided by the user.
     * @param taskList the existing task list
     * @param ui the ui that handles successful/unsuccessful messages
     * @throws DukeException If the task has already been unmarked, an error is thrown
     */

    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            Task taskToBeUnmarked = taskList.getTask(this.taskNumber - 1);
            if (!taskToBeUnmarked.getTaskStatus()) {
                throw new DukeException("Task has already been marked as uncompleted.");
            } else {
                taskToBeUnmarked.markTaskUncompleted();
                return ui.showUnmarkMessage(taskToBeUnmarked);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessages.INVALID_TASK_NUMBER.getMessage());
        }

    }
}
