package martin.commands;

import martin.Ui;

public class ByeCommand implements Command {

    private Ui ui;

    public ByeCommand() {
        this.ui = new Ui();
    }

    /**
    * Exits the chatbot.
    */
    @Override
    public void execute() {
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
