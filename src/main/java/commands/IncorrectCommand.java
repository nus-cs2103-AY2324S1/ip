package commands;

import services.Storage;
import services.TaskList;
import services.UI;

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
