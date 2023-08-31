package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;
import duke.messages.ErrorMessages;

import duke.command.Command;

public class UnmarkCommand extends Command {
    protected int taskNumber;

    public UnmarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

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
