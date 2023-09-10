package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.markTaskAsNotDone(index);
        storage.writeTasksToFile(tasks);
        return ui.printMarkTasksAsNotDone(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
