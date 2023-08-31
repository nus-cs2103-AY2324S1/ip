package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;
import duke.messages.ErrorMessages;

/**
 * Represents a command that deletes a task from the existing task list.
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber ;
    }

    /**
     * Modifies the existing list by deleting the task provided by the user.
     * @param taskList the existing task list
     * @param ui the ui that handles successful/unsuccessful messages
     * @throws DukeException if the task number provided is not in the bounds of the list, an error is thrown
     */
    public void execute (TaskList taskList, Ui ui) throws DukeException {
        try{
            Task taskToBeDeleted = taskList.getTask(this.taskNumber);
            taskList.removeTask(this.taskNumber);
            ui.showDeletedMessage();
            System.out.println(taskToBeDeleted.toString());
            ui.showTaskListSize(taskList);
        } catch (IndexOutOfBoundsException e){
            // Check if task number is within the size of task list.
            throw new DukeException(ErrorMessages.INVALID_TASK_NUMBER.getMessage());
        }
    }
}
