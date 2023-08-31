package Duke.Commands;

import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

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
     * @throws Exception Any exceptions that may occur during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        String content = fullCommand.replaceAll("^\\s*find\\s*", "");
        ui.printRelatedTasks(tasks, content);
    }
}
