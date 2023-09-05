package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a UnmarkCommand class that deals with the command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks a task as done and displays it as undone.
     *
     * @param taskList list of tasks
     * @param storage  storage
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        taskList.unmarkTask(index);
        storage.saveListToDisk(taskList.getTasks());
        return GobbleMessage.getDukeDialog("Nice! I've unmarked this task as undone:\n" + taskList.getTask(index), "Unmark");

    }
}
