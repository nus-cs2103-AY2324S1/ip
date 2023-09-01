package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to quit the chatbot, causing it to bid farewell.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bidFarewell();
    }

    /**
     * Performs a simple boolean check on the current command to check if it is an ExitCommand.
     * @param command The command to be examined.
     * @return The boolean value which tells us if the command is an ExitCommand or not.
     */

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
