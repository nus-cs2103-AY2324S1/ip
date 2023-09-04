package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

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
     * @param ui       user interface
     * @param storage  storage
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
        taskList.unmarkTask(index);
//        ui.showUnmarkMessage(taskList.getTask(index));
        chat.addMessage("Nice! I've unmarked this task as undone:\n" + taskList.getTask(index), "Unmark");
        storage.saveListToDisk(taskList.getTasks());
    }
}
