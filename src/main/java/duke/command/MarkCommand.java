package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Mark that will mark/unmark the task
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class MarkCommand extends Command {
    private final Integer taskIndex;
    private final boolean isMarked;

    /**
     * Constructs a MarkCommand.
     *
     * @param isMarked A boolean that represents if it is a mark or unmark command.
     * @param taskIndex the task index that will be mark or unmark.
     */
    public MarkCommand(boolean isMarked, Integer taskIndex) {
        this.isMarked = isMarked;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the MarkCommand and returns the message.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     * @throws DukeException If there are an invalid Input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        if (isMarked) {
            tasks.mark(taskIndex);
            return ui.showMark(tasks.getTask(taskIndex));
        } else {
            tasks.unmark(taskIndex);
            return ui.showUnmark(tasks.getTask(taskIndex));
        }
    }

    /**
     * Check if it is an ExitCommand.
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
