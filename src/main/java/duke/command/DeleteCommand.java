package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;
import duke.messages.ErrorMessages;

import duke.command.Command;

public class DeleteCommand extends Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber ;
    }
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
