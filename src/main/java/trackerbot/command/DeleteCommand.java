package trackerbot.command;

import java.util.HashSet;
import java.util.Scanner;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;

class DeleteCommand extends Command {
    private final String commandField;

    DeleteCommand(String commandField) {
        this.commandField = commandField;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            int index = getIndexFromFields(commandField);
            uiHandler.setMessage(tasks.deleteTask(index));
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        String[] commandFields = Parser.parseMassOpFields(commandField);
        HashSet<Integer> uniqueIndexes = new HashSet<>();
        StringBuilder errorLog = new StringBuilder("Failed to execute delete on the following: ");

        for (int i = 0; i < commandFields.length; i++) {
            int index = getIndexWithErrLog(commandFields[i].trim(), errorLog);
            addToSetIfUnique(index, uniqueIndexes, errorLog);
        }

        String successLog = tasks.deleteTasks(uniqueIndexes, errorLog);
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
            throw new TrackerBotException("Invalid format: delete [number in list range]");
        }
        int index = scanner.nextInt();

        if (scanner.hasNext()) {
            scanner.close();
            throw new TrackerBotException("Too many fields: delete [number in list range]");
        }
        scanner.close();

        return index;
    }
}
