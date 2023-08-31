package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException;

    public abstract boolean isExit();
}
