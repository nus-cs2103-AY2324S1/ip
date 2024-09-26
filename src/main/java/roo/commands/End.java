package roo.commands;

import roo.TaskList;
import roo.Ui;

public class End extends Command {

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return ui.close();
    }

}
