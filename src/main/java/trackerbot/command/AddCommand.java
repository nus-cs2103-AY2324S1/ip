package trackerbot.command;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;

class AddCommand extends Command {
    private final String commandField;
    private final CommandType type;

    AddCommand(CommandType type, String commandField) {
        this.commandField = commandField;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            uiHandler.setMessage(tasks.addTask(type, commandField));
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        String[] commandFields = Parser.parseMassOpFields(commandField);
        StringBuilder successLog = new StringBuilder("We have successfully added the following tasks:");
        StringBuilder errorLog = new StringBuilder("We have failed to parse the following commands:");

        for (int i = 0; i < commandFields.length; i++) {
            try {
                String successStr = tasks.addTask(type, commandFields[i].trim());
                successLog.append("\n");
                successLog.append(successStr);
            } catch (TrackerBotException e) {
                errorLog.append("\n");
                errorLog.append("Command ");
                errorLog.append(i + 1);
                errorLog.append(": ");
                errorLog.append(e.getMessage());
            }
        }

        successLog.append("\n\n");
        successLog.append(errorLog);
        uiHandler.setMessage(successLog.toString());
    }
}
