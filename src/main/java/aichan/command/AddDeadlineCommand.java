package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Deadline;
import aichan.task.Task;


public class AddDeadlineCommand extends Command {
    private Task task;
    public AddDeadlineCommand(String str) throws AiChanException {
        String[] arr = str.split(" /by ");
        if(arr.length < 2) {
            throw new AiChanException("Behind description, please provide the deadline after ' /by '");
        }
        this.task = new Deadline(arr);
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
