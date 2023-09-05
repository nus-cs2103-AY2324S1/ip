package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * @author juzzztinsoong
 */
public class MarkDoneCommand extends Command {

    private boolean isDone;
    private int index;

    /**
     * Constructor method for MarkDoneCommand.
     * @param isDone true to mark the task as done, false to unmark it.
     * @param index the index of the task.
     */
    public MarkDoneCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.print(tasklist.setDone(isDone, index));
    }

}
