package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to display the command guide to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand instance.
     */
    public HelpCommand() {
        super(null);
    }

    /**
     * Executes the HelpCommand, which provides guidance on available commands to the user.
     *
     * @param tasks   The list of tasks. This is not utilized in the context of the HelpCommand.
     * @param ui      The user interface responsible for displaying messages.
     * @param storage The storage mechanism for tasks. This is not utilized in the context of the HelpCommand.
     * @return A string presenting the user with a guide on available commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCommandGuide();
    }
}