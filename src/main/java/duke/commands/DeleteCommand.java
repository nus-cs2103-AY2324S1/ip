package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents a DeleteCommand where this command should delete a Task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int target;

    /**
     * Public constructor for DeleteCommand
     *
     * @param target the targeted index of the Task to be deleted
     */
    public DeleteCommand(int target) {
        this.target = target;
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException {
        Task task = tasks.getTaskByIndex(this.target);
        if (task == null) {
            throw new InvalidCommandException();
        }
        Task t = tasks.remove(this.target - 1);

        String output = "";
        storage.save(tasks);
        if (!isRestoring) {
            output += ui.showSuccessDelete(t, tasks.size());
        }

        return output;
    }
}
