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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printNotSureMessage();
    }
}
