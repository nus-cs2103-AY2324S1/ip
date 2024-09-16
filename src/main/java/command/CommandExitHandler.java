package command;
import main.Main;
import main.Ui;

public class CommandExitHandler implements ICommandHandler {

    /**
     * Execute the command
     *
     * @param command the full command
     * @param parameters the command parameters (splited by spaces)
     * @throws CommandException when any exception happens during command execution
     */
    @Override
    public void execute(String command, String[] parameters) {
        assert(Ui.getInstance() != null);
        Ui.getInstance().byeAndExit();
    }
}
