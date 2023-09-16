package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;

class FindCommand extends Command {
    private final String commandField;

    FindCommand(String commandFields) {
        this.commandField = commandFields;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setMessage(tasks.findAll(commandField.trim()));
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        String[] commandFields = Parser.parseMassOpFields(commandField);
        StringBuilder successLog = new StringBuilder("Here are your query results: ");

        for (String searchStr: commandFields) {
            successLog.append("\n\n");
            successLog.append(searchStr.trim());
            successLog.append(":\n");
            successLog.append(tasks.findAll(searchStr.trim()));
        }

        uiHandler.setMessage(successLog.toString());
    }
}
