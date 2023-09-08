package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        System.out.println(Ui.LINE);
        System.out.println("I'm sorry, but I don't know what that means :-(");
        System.out.println(Ui.LINE);
    }
    
}
