package duke.command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;

/**
 * The ByeCommand class represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a new ByeCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the bye command, indicating that the application should exit.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object (not used in this command).
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) {
        this.exit = true;
    }
}
