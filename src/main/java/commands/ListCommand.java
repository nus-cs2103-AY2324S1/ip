package commands;

import ari.AriException;
import ari.Storage;
import ari.TaskList;
import ari.Ui;

import java.io.IOException;

/**
 * ListCommand class for command "list"
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AriException {
        return ui.printList(tasks);
    }
}
