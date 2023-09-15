package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

public class MassCommand extends Command {
    private final String commandFields;
    private final CommandType type;

    MassCommand(CommandType type, String commandFields) {
        this.type = type;
        this.commandFields = commandFields;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setMessage(tasks.list());
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        throw new TrackerBotException("I can't run nested mass operations!");
    }
}
