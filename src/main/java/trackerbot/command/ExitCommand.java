package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class ExitCommand extends Command {
    ExitCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.exitApp();
    }
}
