package chatty.command;

import chatty.task.Task;
import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class ToDoCommand extends Command {

    private Task task;

    public ToDoCommand(String taskDescription) {
        super(false);
        this.task = new ToDo(taskDescription);
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
