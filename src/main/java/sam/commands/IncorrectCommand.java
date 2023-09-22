package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(feedbackToUser);
        return new CommandResult(Message.ERROR, " " + feedbackToUser);
    }
}
