package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;
import aichan.task.ToDo;

/**
 * Represents a command to add a ToDo object into task list.
 * Inherits from the Command class.
 */
public class AddToDoCommand extends Command {
    private Task task;

    /**
     * Constructs an AddToDoCommand object.
     *
     * @param str String containing description of a todo.
     */
    public AddToDoCommand(String str) {
        this.task = new ToDo(str);
    }

    /**
     * Adds the ToDo object into task list, informs user, and save to file.
     *
     * @param tasks A list of tasks.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If any error occur when save ToDo object into storage.
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
