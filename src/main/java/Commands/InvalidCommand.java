package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
public class InvalidCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to print out the message that indicates to the user that they have entered an invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printNotSureMessage();
    }
}
