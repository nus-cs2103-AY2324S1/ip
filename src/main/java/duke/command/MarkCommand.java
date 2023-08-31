package duke.command;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.messages.ErrorMessages;

import duke.command.Command;

public class MarkCommand extends Command {
    protected int taskNumber;

    public MarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

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
