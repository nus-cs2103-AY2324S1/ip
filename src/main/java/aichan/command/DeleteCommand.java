package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

public class DeleteCommand extends Command {
    private int taskId;
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        // control the main logic
        // add/delete task, show words
        int size = tasks.getSize();
        if (taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
        Task task = tasks.deleteTask(taskId);
        ui.showMessage(String.format("Noted. I've removed this task:\n  %s\n" +
                "Now you have %d tasks in the list", task, size - 1));
        storage.save(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
