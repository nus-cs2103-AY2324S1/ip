package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Command to edit a task in the task.Task List.
 */
public class EditCommand extends Command {
    /** The changes to the task */
    private final String editType;

    /** The task to be changed */
    private final int ind;

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
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        return tasks.editTask(editType, ind);
    }

}
