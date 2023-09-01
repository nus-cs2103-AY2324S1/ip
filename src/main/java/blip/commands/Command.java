package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

public abstract class Command {
    public abstract void execute(TaskList taskList, BlipUI ui, BlipStorage storage);


}
