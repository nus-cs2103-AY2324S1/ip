package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a HelpCommand class that deals with the help command.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        return GobbleMessage.getGobbleDialog("Here are the list of commands:\n"
                + "1. todo <description>\n"
                + "2. deadline <description> /by <date>\n"
                + "3. event <description> /at <date>\n"
                + "4. list\n"
                + "5. done <task number>\n"
                + "6. delete <task number>\n"
                + "7. find <keyword>\n"
                + "8. bye");
    }
}
