package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Clear extends Commands {

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return ui.clear();
    }

}
