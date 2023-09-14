package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class AddCommand extends Command {
    private final String commandFields;
    private final CommandType type;

    AddCommand(CommandType type, String commandFields) {
        this.commandFields = commandFields;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        uiHandler.setMessage(tasks.add(type, commandFields));
    }
}
