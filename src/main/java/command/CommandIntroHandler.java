package command;
import main.Main;
import main.Ui;

public class CommandIntroHandler implements ICommandHandler {

    /**
     * Execute the command
     *
     * @param command the full command
     * @param parameters the command parameters (splited by spaces)
     * @throws CommandException when any exception happens during command execution
     */
    @Override
    public void execute(String command, String[] parameters) {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getName() != null);
        assert(Ui.getInstance() != null);
        String name = Main.getInstance().getName();
        Ui.getInstance().say("Hello! I'm " + name + "\nWhat can I do for you?");
    }
}
