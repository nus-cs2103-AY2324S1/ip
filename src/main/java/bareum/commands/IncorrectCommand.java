package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for invalid inputs.
 */
public class IncorrectCommand extends Command {
    public IncorrectCommand() {

    }

    /**
     * Informs the user that their input was invalid
     * @param ui Lets the user know that their input was invalid.
     * @param storage Storage is not used in this method.
     * @param taskList Task list is not used in this method.
     * @throws BareumException If user input was invalid.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        Ui.reply("Oops! I'm sorry but I don't know what that means :(");
    }
}
