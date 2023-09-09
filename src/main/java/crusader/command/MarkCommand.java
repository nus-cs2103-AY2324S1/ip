package crusader.command;

import crusader.TaskList;
import crusader.Ui;
import crusader.exception.CrusaderException;
import crusader.task.Task;

/**
 * Command used to mark or unmark tasks as done or not in the bot
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    /**
     * Constructs a new marking command.
     *
     * @param index     The index of the task to mark.
     * @param isMark    Whether we are to mark or unmark.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws CrusaderException {
        Task changedTask = this.isMark ? taskList.markTask(index) : taskList.unmarkTask(index);
        ui.say(String.format(
                "%s:\n%s\n",
                this.isMark ? "I have marked the following task as done" : "I have unmarked this task",
                changedTask.toString()));
    }
}
