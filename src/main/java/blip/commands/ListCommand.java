package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        ui.listsTasksMsg(taskList);
    }
}
