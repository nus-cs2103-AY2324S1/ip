package aichan.command;

import aichan.AiChanException;
import aichan.TaskList;
import aichan.Storage;
import aichan.Ui;
import aichan.task.Task;

public class MarkCommand extends Command{
    private int taskId;
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        int size = tasks.getSize();
        if (taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
        Task task = tasks.getTask(taskId);
        task.mark();
        ui.showMessage("Nice! I've marked this task as done:\n"
                + task.toString());
        storage.save(tasks);
    }
    @Override
    public boolean isExit(){
        return false;
    }
}
