package Alex.commands;

import Alex.ui.Ui;

/**
 * A class that represents the command for exiting the execution of Alex bot.
 */
public class ExitCommand extends Command {
    private String command;
    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute() {
        String output = Ui.bye();
        System.exit(0);
        return output;
    }
}
