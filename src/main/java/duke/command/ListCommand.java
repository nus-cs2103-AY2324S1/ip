package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String returnMessage = "Looking at your tasks again to remind yourself is good,"
                + " but remember to actually do them:";
        for (int i = 1; i <= tasks.getLength(); i++) {
            Task t = tasks.getTask((i-1));
            returnMessage.concat(i + ". " + t.toString() + "\n");
        }
        return returnMessage;
    }
}