package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Deadline;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class DeadlineCommand extends Command {

    private Task task;

    public DeadlineCommand(String taskDescription, String deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
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
