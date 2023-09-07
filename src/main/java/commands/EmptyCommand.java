package commands;

import data.TaskList;
import parser.Parser;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

/**
 * The EmptyCommand class.
 * Returned by {@link Parser} when no command is given.
 */
public class EmptyCommand extends Command {
    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage, UiCli uiCli) {
        return new UiMessage();
    }
}
