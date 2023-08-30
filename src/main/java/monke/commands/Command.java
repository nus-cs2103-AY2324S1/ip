package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException;

    public boolean isExit() {
        return false;
    }
}
