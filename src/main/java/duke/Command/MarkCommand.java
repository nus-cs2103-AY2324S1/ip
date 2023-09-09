package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.markTaskAsDone(index);
        storage.writeTasksToFile(tasks);
        ui.printMarkTasksAsDone(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

