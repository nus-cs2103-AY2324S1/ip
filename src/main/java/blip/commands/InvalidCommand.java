package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents the invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Description of the invalid command.
     */
    String description;

    /**
     * Constructor of InvalidCommand.
     * @param description The description of the invalid command
     */
    public InvalidCommand(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    /**
     * Executes the necessary messages for an invalid command.
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     */
=======
>>>>>>> branch-A-CodingStandard
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        description = description.toLowerCase();
        if (!containsCommands(description)) {
            ui.showInvalidCmdErr();
            ui.showListOfOtherCmds();
        }
    }

    /**
     * Checks if there are any valid commands of Blip ChatBot.
     * @param input The input of command
     * @return boolean true if the input contains any command words at all, false otherwise
     */
    public boolean containsCommands(String input) {
        input = input.toLowerCase();
        if (description.contains("bye ") || description.contains("list ") || description.contains("mark ")
                || description.contains("unmark ") || description.contains("delete ") || description.contains("todo ")
                || description.contains("deadline ") || description.contains("event ")) {
            return true;
        } else {
            return false;
        }
    }

}
