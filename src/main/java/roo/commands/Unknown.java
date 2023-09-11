package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Unknown extends Command {

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return "I dunno what u mean!!!\n";
    }

}
