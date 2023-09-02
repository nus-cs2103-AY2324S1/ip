package haste.commands;

import haste.data.TaskList;
import haste.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
