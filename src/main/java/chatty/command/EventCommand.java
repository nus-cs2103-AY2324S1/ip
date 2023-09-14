package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the event command that the user entered
 */
public class EventCommand extends Command {

    private Task task;

    /**
     * Contructor for event command that will create a task object
     * @param taskDescription the event task to be added
     * @param startTime the timing which the event start
     * @param endTIme the timing which the event will end
     */
    public EventCommand(String taskDescription, String startTime, String endTIme) {
        super(false);
        this.task = new Event(taskDescription, startTime, endTIme);
    }

    /**
     * Add in the new event task into the current taskList
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that tells the use that the event task has been added into
     *          the list successfully
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
