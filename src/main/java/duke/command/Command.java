package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
