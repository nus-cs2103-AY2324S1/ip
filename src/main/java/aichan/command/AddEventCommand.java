package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Event;
import aichan.task.Task;

/**
 * Represents a command to add an Event object into task list.
 * Inherits from the Command class.
 */
public class AddEventCommand extends Command {
    private Task task;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param str String containing description, start and end date of an event.
     * @throws AiChanException If any date is missing.
     */
    public AddEventCommand(String str) throws AiChanException {
        String[] arr = str.split(" /from | /to ");
        if(arr.length < 3) {
            throw new AiChanException("Behind description, " +
                    "please provide \nthe respective date/time after ' /from ' and ' /to '");
        }
        this.task = new Event(arr);
    }

    /**
     * Adds the Event object into task list, informs user, and save to file.
     *
     * @param tasks A list of tasks.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If any error occur when save Event object into storage.
     */
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
