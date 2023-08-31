package command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import exception.DukeException;

/**
 * command.Command to edit a task in the task.Task List.
 */
public class EditCommand extends Command {
    /** The changes to the task */
    private String editType;

    /** The task to be changed */
    private int ind;

    /**
     * Constructs a new Edit command.Command. Can mark, unmark task as done or
     * delete a task.
     *
     * @param editType The edit command.
     * @param ind The task to be changed.
     */
    public EditCommand(String editType, int ind) {
        this.editType = editType;
        this.ind = ind;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        String action = tasks.editTask(editType, ind);
        ui.respondUser(action);
    }
    @Override
    public boolean isExit() {
        return false;
    };
}
