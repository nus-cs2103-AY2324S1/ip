package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

/**
 * Stops running the jelly Chat Bot.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

    public boolean isRunning() {
        return false;
    }
}
