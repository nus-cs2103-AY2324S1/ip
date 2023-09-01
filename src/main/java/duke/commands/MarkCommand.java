package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String COMMAND_WORD_UNMARK = "unmark";
    private boolean isMark;
    private int target;

    public MarkCommand(boolean isMark, int target) {
        this.isMark = isMark;
        this.target = target;
    }
    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException  {
        Task task = tasks.getTaskByIndex(this.target);
        if (task == null) {
            throw new InvalidCommandException();
        }

        task.setMarked(this.isMark);
        storage.save(tasks);

        if (!isRestoring) {
            ui.showSuccessMark(this.isMark, task);
        }
    }
}
