package commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;
}
