package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.storage.BlipStorage;
import javafx.application.Platform;


/**
 * Represents the bye command that will end the Blip ChatBot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command that will end the Blip ChatBot.
     *
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        Platform.exit();
        return null;
    }
}
