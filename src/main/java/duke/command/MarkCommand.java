package duke.command;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.messages.ErrorMessages;

/**
 * A command that marks a specified task as Completed
 */

public class MarkCommand extends Command {
    protected int taskNumber;

    public MarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the specific task provided by the user.
     * @param taskList the existing task list
     * @param ui the ui that handles successful/unsuccessful messages
     * @throws DukeException If the task has already been marked, an error is thrown
     */
    public void execute(TaskList taskList, Ui ui)throws DukeException {
        try {
            Task taskToBeMarked= taskList.getTask(this.taskNumber);
            if (taskToBeMarked.isTaskCompleted()){
                throw new DukeException("Task has already been marked as completed.");
            }
            else {
                taskToBeMarked.markTaskCompleted();
                ui.showMarkMessage();
                System.out.println(taskToBeMarked.toString());
            }
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(ErrorMessages.INVALID_TASK_NUMBER.getMessage());
        }

    }
}
