package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the todo command that the user entered
 */
public class ToDoCommand extends Command {

    private Task task;

    /**
     * Contructor for find command that will create a task object
     * @param taskDescription the task to be added into the list
     */
    public ToDoCommand(String taskDescription) {
        super(false);
        this.task = new ToDo(taskDescription);
    }

    /**
     * Add in the new todo task into the list
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that tells the user that the task has been added
     * @throws ChattyException when storage could not update the files
     */
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
