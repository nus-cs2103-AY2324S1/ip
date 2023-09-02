package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        super(CommandType.DELETE);
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            tasks.deleteTask(taskNum - 1);
            ui.deleteMessage(task);
            ui.taskListSizeMessage(tasks.getSize(), false);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}
