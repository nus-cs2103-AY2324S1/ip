package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Clear extends Command {

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return ui.clear();
    }

}
