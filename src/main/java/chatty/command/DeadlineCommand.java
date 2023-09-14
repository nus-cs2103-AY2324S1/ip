package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Deadline;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the deadline command that the user entered
 */
public class DeadlineCommand extends Command {

    private Task task;

    /**
     * Contructor for deadline command that will create a task object
     * @param taskDescription the deadline task that user wants to add in
     * @param deadline the deadline of the task
     */
    public DeadlineCommand(String taskDescription, String deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
    }

    /**
     * Add in the deadline task into the current tasklist
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that shows the user that the task has been added successfully
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
