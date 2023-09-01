package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

/**
 * Represents a MarkCommand which should set the marked value of a Task.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String COMMAND_WORD_UNMARK = "unmark";
    private boolean isMarked;
    private int target;

    /**
     * Public constructor for MarkCommand.
     *
     * @param isMarked boolean value if the Task should be marked
     * @param target the targeted index of the Task to be marked
     */
    public MarkCommand(boolean isMarked, int target) {
        this.isMarked = isMarked;
        this.target = target;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException  {
        Task task = tasks.getTaskByIndex(this.target);
        if (task == null) {
            throw new InvalidCommandException();
        }

        task.setMarked(this.isMarked);
        storage.save(tasks);

        if (!isRestoring) {
            ui.showSuccessMark(this.isMarked, task);
        }
    }
}
