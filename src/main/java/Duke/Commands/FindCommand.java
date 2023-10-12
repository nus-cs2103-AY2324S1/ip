package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to find tasks matching a specific keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand with the given full command.
     *
     * @param fullCommand The full command string.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the FindCommand using the provided task list, user interface, and storage.
     * Print tasks matching the provided keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for data persistence.
     * @return A string containing the result of executing the FindCommand.
     * @throws Exception Any exceptions that may occur during command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        // Removes the command type from the full command
        String content = fullCommand.replaceAll("^\\s*find\\s*", "");
        return ui.printRelatedTasks(tasks, content);
    }
}
