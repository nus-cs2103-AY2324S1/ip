package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command{
    private int index;

    private boolean isDone;

    public MarkCommand(int index, String type) {
        this.index = index;
        this.isDone = type.equals("mark");
    }

    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() < index || tasks.getTask(index - 1).isDone() == isDone) {
            throw new DukeException("The task you are trying to mark is either out of bound, " +
                    "or has already been marked/unmarked");
        }

        if (isDone) {
            tasks.setTaskAsDone(index);
            ui.acknowledgeMark(index, tasks.getTask(index - 1));
        } else {
            tasks.setTaskAsUndone(index);
            ui.acknowledgeUnmark(index, tasks.getTask(index - 1));
        }

        storage.updateStorage(tasks.getArrayList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
