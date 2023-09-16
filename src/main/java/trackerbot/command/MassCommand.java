package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;

class MassCommand extends Command {
    private final String commandFields;

    MassCommand(String commandFields) {
        this.commandFields = commandFields;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            Command nestedCommand = Parser.parseCommand(commandFields);
            nestedCommand.executeAsMassOp(tasks, uiHandler);
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        throw new TrackerBotException("I can't run nested mass operations!");
    }
}
