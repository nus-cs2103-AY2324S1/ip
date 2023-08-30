package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.UI;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printMessage(feedbackToUser);
    }
}
