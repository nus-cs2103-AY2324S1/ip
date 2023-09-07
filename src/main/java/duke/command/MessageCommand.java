package duke.command;

import duke.*;

public class MessageCommand extends Command {
    private String message;
    public MessageCommand(String message) {
        this.message = message;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(message);
    }
}
