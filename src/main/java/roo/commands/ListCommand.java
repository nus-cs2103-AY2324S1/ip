package roo.commands;

import roo.TaskList;
import roo.Ui;

/**
 * A command for listing all tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return ui.list();
    }

}
