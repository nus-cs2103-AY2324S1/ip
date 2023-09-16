package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class ListCommand extends Command {
    ListCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setMessage(tasks.list());
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        throw new TrackerBotException("List command cannot be executed as a mass operation.");
    }
}
