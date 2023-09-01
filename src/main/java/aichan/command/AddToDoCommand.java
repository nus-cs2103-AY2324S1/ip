package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;
import aichan.task.ToDo;

public class AddToDoCommand extends Command {
    private Task task;
    public AddToDoCommand(String str) {
        this.task = new ToDo(str);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        tasks.addTask(task);
        int size = tasks.getSize();
        ui.showMessage(String.format("Got it. I've added this task:\n  %s\n" +
                "Now you have %d tasks in the list", task, size));
        storage.save(tasks);
    }
    @Override
    public boolean isExit(){
        return false;
    }
}
