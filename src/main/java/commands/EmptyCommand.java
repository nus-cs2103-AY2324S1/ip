package commands;

import data.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * The EmptyCommand class.
 * Returned by {@link Parser} when no command is given.
 */
public class EmptyCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        return;
    }
}
