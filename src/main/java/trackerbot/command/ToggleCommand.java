package trackerbot.command;

import java.util.HashSet;
import java.util.Scanner;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;

class ToggleCommand extends Command {
    private final String commandField;
    private final CommandType type;

    ToggleCommand(CommandType type, String commandField) {
        this.commandField = commandField;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            int index = getIndexFromFields(commandField);

            switch (type) {
            case MARK:
                uiHandler.setMessage(tasks.markTask(index));
                break;
            case UNMARK:
                uiHandler.setMessage(tasks.unmarkTask(index));
                break;
            default:
                throw new IllegalStateException("Created ToggleCommand with invalid field.");
            }
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        String[] commandFields = Parser.parseMassOpFields(commandField);
        HashSet<Integer> uniqueIndexes = new HashSet<>();
        StringBuilder errorLog = new StringBuilder("Failed to toggle the following tasks: ");

        for (int i = 0; i < commandFields.length; i++) {
            int index = getIndexWithErrLog(commandFields[i].trim(), errorLog);
            addToSetIfUnique(index, uniqueIndexes, errorLog);
        }

        String successLog;
        switch (type) {
        case MARK:
            successLog = tasks.markTasks(uniqueIndexes, errorLog);
            break;
        case UNMARK:
            successLog = tasks.unmarkTasks(uniqueIndexes, errorLog);
            break;
        default:
            throw new IllegalStateException("Created ToggleCommand with invalid field.");
        }
        uiHandler.setMessage(successLog + errorLog);
    }

    private int getIndexWithErrLog(String commandField, StringBuilder errorLog) {
        try {
            return getIndexFromFields(commandField);
        } catch (TrackerBotException e) {
            errorLog.append("\n");
            errorLog.append(commandField);
            errorLog.append(" - ");
            errorLog.append(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    private void addToSetIfUnique(int index, HashSet<Integer> uniqueIndexes, StringBuilder errorLog) {
        if (index == Integer.MIN_VALUE) {
            return;
        }

        if (uniqueIndexes.contains(index)) {
            errorLog.append("\n");
            errorLog.append(index);
            errorLog.append(" - ");
            errorLog.append("Duplicate index!");
        }

        uniqueIndexes.add(index);
    }

    private int getIndexFromFields(String commandField) throws TrackerBotException {
        Scanner scanner = new Scanner(commandField);
        if (!scanner.hasNextInt()) {
            scanner.close();
            throw new TrackerBotException("Invalid format: mark/unmark [number in list range]");
        }
        int index = scanner.nextInt();

        if (scanner.hasNext()) {
            scanner.close();
            throw new TrackerBotException("Too many fields: mark/unmark [number in list range]");
        }
        scanner.close();

        return index;
    }
}
