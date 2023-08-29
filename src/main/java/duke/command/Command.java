package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    public boolean isExit() {
        return false;
    }
}
