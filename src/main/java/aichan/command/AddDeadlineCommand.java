package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Deadline;
import aichan.task.Task;

/**
 * Represents a command to add a Deadline object into task list.
 * Inherits from the Command class.
 */
public class AddDeadlineCommand extends Command {
    private Task task;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param str String containing description and deadline of a task.
     * @throws AiChanException If the due date is missing.
     */
    public AddDeadlineCommand(String str) throws AiChanException {
        String[] arr = str.split(" /by ");
        if(arr.length < 2) {
            throw new AiChanException("Behind description, please provide the deadline after ' /by '");
        }
        this.task = new Deadline(arr);
    }

    /**
     * Adds the Deadline object into task list, informs user, and save to file.
     *
     * @param tasks A list of tasks.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If any error occur when save Deadline object into storage.
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
