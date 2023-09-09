package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.markTaskAsNotDone(index);
        storage.writeTasksToFile(tasks);
        ui.printMarkTasksAsNotDone(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
