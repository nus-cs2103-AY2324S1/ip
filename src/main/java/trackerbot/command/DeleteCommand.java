package trackerbot.command;

import java.util.Scanner;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class DeleteCommand extends Command {
    private final String commandFields;

    DeleteCommand(String commandFields) {
        this.commandFields = commandFields;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) throws TrackerBotException {
        int index = getIndexFromFields();
        uiHandler.setMessage(tasks.delete(index));
    }

    private int getIndexFromFields() throws TrackerBotException {
        Scanner scanner = new Scanner(commandFields);
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
