package commands;

import ari.AriException;
import ari.Storage;
import ari.TaskList;
import ari.Ui;

import java.io.IOException;

/**
 * ByeCommand class for the command "bye"
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AriException {
        return ui.bye();
    }
}
