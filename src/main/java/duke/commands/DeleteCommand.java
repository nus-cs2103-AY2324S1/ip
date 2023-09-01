package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public int target;
    public DeleteCommand(int target) {
        this.target = target;
    }

    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException {
        Task task = tasks.getTaskByIndex(this.target);
        if (task == null) {
            throw new InvalidCommandException();
        }
        Task t = tasks.remove(this.target - 1);
        storage.save(tasks);
        if (!isRestoring) {
            ui.showSuccessDelete(t, tasks.size());
        }
    }
}
