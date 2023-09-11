package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a AddCommand class that deals with the command to add a task.
 */
public class TodoCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task Task to be added.
     */
    public TodoCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskList TaskList list of tasks.
     * @param storage  Storage.
     * @return GobbleMessage object containing the message to be displayed.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        taskList.addTask(task);
        storage.saveListToDisk(taskList.getTasks());

        return GobbleMessage.getGobbleDialog("Got it. I've added this task:\n" + task + "\nNow you have "
                + taskList.getSize() + " tasks in the list.");
    }
}
