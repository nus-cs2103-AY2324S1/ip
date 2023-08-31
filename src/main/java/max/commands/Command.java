package max.commands;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;


public class Command {
    public Command() {

    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        throw new MaxException("This command cannot be executed bro.");
    }
    public boolean isExit() {
        return false;
    }
}
