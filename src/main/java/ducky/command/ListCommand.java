package ducky.command;

import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.showMessagePerLine(taskList.getPrintableList());
    }
}
