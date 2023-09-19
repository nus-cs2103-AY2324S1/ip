package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.storage.BlipStorage;
/**
 * Represents the invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Description of the invalid command.
     */
    String description;

    /**
     * Creates an instance of InvalidCommand.
     *
     * @param description The description of the invalid command
     */
    public InvalidCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the necessary messages for an invalid command.
     *
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */

    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        description = description.toLowerCase();
        if (!containsCommands(description)) {
            return ui.showInvalidCmdErr();
        }
        return "";
    }

    /**
     * Checks if there are any valid commands of Blip ChatBot.
     *
     * @param input The input of command
     * @return boolean true if the input contains any command words at all, false otherwise
     */
    public boolean containsCommands(String input) {
        String inputLowerCase = input.toLowerCase();
        if (inputLowerCase.contains("bye ") || inputLowerCase.contains("list ") || inputLowerCase.contains("mark ")
                || inputLowerCase.contains("unmark ") || inputLowerCase.contains("delete ") || inputLowerCase.contains("todo ")
                || inputLowerCase.contains("deadline ") || inputLowerCase.contains("event ")) {
            return true;
        } else {
            return false;
        }
    }

}
