package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents a Command for Blip.
 */
public abstract class Command {
    /**
     * Executes the command for Blip
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     */
    public abstract void execute(TaskList taskList, BlipUI ui, BlipStorage storage);


}
