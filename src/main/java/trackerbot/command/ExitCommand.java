package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class ExitCommand extends Command {
    ExitCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.exitApp();
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        throw new TrackerBotException("Exit command cannot be executed as a mass operation.");
    }
}
