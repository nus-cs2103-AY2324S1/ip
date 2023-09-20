package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String returnMessage = "Looking at your tasks again to remind yourself is good,"
                + " but remember to actually do them:\n"
                + tasks.toString();
        return returnMessage;
    }
}