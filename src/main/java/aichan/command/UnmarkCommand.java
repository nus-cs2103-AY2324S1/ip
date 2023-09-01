package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

public class UnmarkCommand extends Command {
    private int taskId;
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        int size = tasks.getSize();
        if (taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
        Task task = tasks.getTask(taskId);
        task.unmark();
        ui.showMessage("OK, I've marked this task as not done yet:\n"
                + task.toString());
        storage.save(tasks);
    }
    @Override
    public boolean isExit(){
        return false;
    }
}
