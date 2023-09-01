package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

public class InvalidCommand extends Command {
    String description;

    public InvalidCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        description = description.toLowerCase();
        if (!containsCommands(description)) {
            ui.showInvalidCmdErr();
            ui.showListOfOtherCmds();
        }
    }

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
