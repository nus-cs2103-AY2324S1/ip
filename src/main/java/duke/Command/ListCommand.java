package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        storage.writeTasksToFile(tasks);
        ui.printListMessage(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }

}
