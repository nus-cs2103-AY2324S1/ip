package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
