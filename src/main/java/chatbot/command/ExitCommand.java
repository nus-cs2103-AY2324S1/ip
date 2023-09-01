package chatbot.command;

import chatbot.Ui;
import chatbot.task.TaskManager;

/**
 * class which handle exit command extends abstract class command.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.showFarewell();

    }

    @Override
    public boolean isExit() {
        return true;
    }
}

