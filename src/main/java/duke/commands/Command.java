package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract String execute(TaskList tasks) throws DukeException;
}
