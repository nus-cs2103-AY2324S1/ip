package chatbot.command;

import chatbot.Ui;
import chatbot.task.TaskManager;

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

