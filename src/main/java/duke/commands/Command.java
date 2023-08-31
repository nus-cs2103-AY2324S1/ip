package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
