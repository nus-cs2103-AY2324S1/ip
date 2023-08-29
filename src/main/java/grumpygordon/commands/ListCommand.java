package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;

public class ListCommand extends Command {

    public ListCommand() {
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandMessage(tasks.toString());
    }
}
