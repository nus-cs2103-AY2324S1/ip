package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.exceptions.DukeException;

public class MarkDoneCommand extends Command {

    private boolean isDone;
    private int index;

    public MarkDoneCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.print(tasklist.setDone(isDone, index));
    }

}
