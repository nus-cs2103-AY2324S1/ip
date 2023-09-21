package duke.command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;

/**
 * The DisplayCommand class represents a command to display the list of tasks.
 */
public class DisplayCommand extends Command {
    /**
     * Constructs a new DisplayCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public DisplayCommand(String command) {
        super(command);
    }

    /**
     * Executes the display command, printing the list of tasks.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object (not used in this command).
     */
    @Override
    public String execute(DukeList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }
}
