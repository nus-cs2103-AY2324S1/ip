import exceptions.DukeException;
import exceptions.InvalidCommandException;

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
        Task task = tasks.getTask(this.target);
        if (task == null) {
            throw new InvalidCommandException();
        }

        task.mark(this.isMark, false);
        storage.save(tasks);
        if (!isRestoring) {
            ui.showSuccessMark(this.isMark, task);
        }
    }
}
