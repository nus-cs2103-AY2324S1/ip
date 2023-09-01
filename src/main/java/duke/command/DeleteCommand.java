package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index
     * of the task to be deleted
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(index >= 0 && index < tasks.getLength()) {
            ui.deletePrint(tasks.getTask(index), tasks.getTaskCount() - 1);
            tasks.remove(index);
        }
    }
}