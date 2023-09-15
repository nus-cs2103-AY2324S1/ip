package trackerbot.command;

import java.util.Scanner;

import trackerbot.exception.TrackerBotException;
import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class ToggleCommand extends Command {
    private final String commandFields;
    private final CommandType type;

    ToggleCommand(CommandType type, String commandFields) {
        this.commandFields = commandFields;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        try {
            int index = getIndexFromFields();

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
        // TODO: Implement the mass operation
    }

    private int getIndexFromFields() throws TrackerBotException {
        Scanner scanner = new Scanner(commandFields);
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
