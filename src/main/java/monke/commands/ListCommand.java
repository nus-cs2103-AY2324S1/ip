package monke.commands;

import monke.Storage;
import monke.TaskList;
import monke.Ui;

/**
 * The ListCommand class represents a command to list tasks in the Monke application.
 * It extends the Command class.
 */
public class ListCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the ListCommand to display the list of tasks.
     *
     * @param ui The user interface.
     * @param storage The storage to read data from.
     * @param tasks The list of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.displayList(tasks);
    }
}