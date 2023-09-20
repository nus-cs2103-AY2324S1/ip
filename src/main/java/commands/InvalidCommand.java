package commands;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/**
 * The command that tells the user to try a different command.
 */
public class InvalidCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to print out the message which
     * indicates to the user that they have entered an invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getNotSureMessage();
    }
}
