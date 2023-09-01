package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Event;
import aichan.task.Task;

public class AddEventCommand extends Command {
    private Task task;
    public AddEventCommand(String str) throws AiChanException {
        String[] arr = str.split(" /from | /to ");
        if(arr.length < 3) {
            throw new AiChanException("Behind description, " +
                    "please provide \nthe respective date/time after ' /from ' and ' /to '");
        }
        this.task = new Event(arr);
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
