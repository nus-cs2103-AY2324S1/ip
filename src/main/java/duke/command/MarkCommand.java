package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int taskNum;

    public MarkCommand(int taskNum) {
        super(CommandType.MARK);
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            task.changeStatus(true);
            ui.markTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
        }
    }
}
