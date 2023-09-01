package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents the bye command that will end the Blip ChatBot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command that will end the Blip ChatBot.
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     */
    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        ui.showOutro();
    }
}
