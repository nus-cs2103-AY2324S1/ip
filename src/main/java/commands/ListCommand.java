package commands;

import functions.*;
import java.io.IOException;

/**
 * Represents a command to list tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {}

    /**
     * Executes the ListCommand to display the list of tasks.
     *
     * @param tasks   The TaskList to list tasks from.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface (not used in this command).
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.showListMsg(tasks.showList());
    }
}

