package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;
import duke.messages.ErrorMessages;

/**
 * A command that unmarks a specified task
 */

public class UnmarkCommand extends Command {
    protected int taskNumber;

    public UnmarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * Unmarks the specific task provided by the user.
     * @param taskList the existing task list
     * @param ui the ui that handles successful/unsuccessful messages
     * @throws DukeException If the task has already been unmarked, an error is thrown
     */

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            Task taskToBeUnmarked = taskList.getTask(this.taskNumber);
            if (!taskToBeUnmarked.isTaskCompleted()){
                throw new DukeException("Task has already been marked as uncompleted.");
            }
            taskToBeUnmarked.markTaskUncompleted();
            ui.showUnmarkMessage();
            System.out.println(taskToBeUnmarked.toString());
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(ErrorMessages.INVALID_TASK_NUMBER.getMessage());
        }

    }
}
