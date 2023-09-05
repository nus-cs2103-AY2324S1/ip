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
     * @param task to be added.
     */
    public TodoCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskList list of tasks.
     * @param storage  storage.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        taskList.addTask(task);
        storage.saveListToDisk(taskList.getTasks());

        return GobbleMessage.getDukeDialog("Got it. I've added this task:\n" + task + "\nNow you have "
                + taskList.getSize() + " tasks in the list.", "Add");
    }
}
