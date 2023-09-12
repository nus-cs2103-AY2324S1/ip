package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class EventCommand extends Command {

    private Task task;

    public EventCommand(String taskDescription, String startTime, String endTIme) {
        super(false);
        this.task = new Event(taskDescription, startTime, endTIme);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        taskList.addTask(this.task);

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showAdded(this.task, taskList);
    }
}
