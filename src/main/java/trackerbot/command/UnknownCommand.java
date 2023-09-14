package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class UnknownCommand extends Command {
    UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        throw new TrackerBotException("Unrecognised Command Type. Try another?");
    }
}
