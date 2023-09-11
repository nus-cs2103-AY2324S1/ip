package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a MarkCommand class that deals with the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and displays it as done.
     *
     * @param taskList list of tasks
     * @param storage  storage
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        taskList.markTask(index);
        storage.saveListToDisk(taskList.getTasks());
        return GobbleMessage.getGobbleDialog("Nice! I've marked this task as done:\n" + taskList.getTask(index), "Mark");
    }
}
