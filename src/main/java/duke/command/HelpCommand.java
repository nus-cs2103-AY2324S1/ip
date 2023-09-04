package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all available commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     * @throws DukeException If there's an error while parsing the user input or updating the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String str = "Here are the available commands:\n" +
                "1. todo <task>\n" +
                "2. deadline <task> /by <date>\n" +
                "3. event <task> /from <date> /to <date>\n" +
                "4. mark <index>\n" +
                "5. unmark <index>\n" +
                "6. delete <index>\n" +
                "7. find <index>\n" +
                "8. list\n" +
                "9. bye";

        ui.sendMessage(str);
    }

    /**
     * Indicates whether this command is an exit command.
     * HelpCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
