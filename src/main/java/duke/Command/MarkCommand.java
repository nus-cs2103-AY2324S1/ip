package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.markTaskAsDone(index);
        storage.writeTasksToFile(tasks);
        return ui.printMarkTasksAsDone(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

