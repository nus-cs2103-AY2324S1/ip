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
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            uiHandler.setMessage(tasks.addTask(type, commandFields));
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }
    }
}
