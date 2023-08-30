package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = taskList.getTask(index);
        temp.unmark();
        ui.printUnmarkMessage(temp);
        storage.save(taskList);
    }
}
