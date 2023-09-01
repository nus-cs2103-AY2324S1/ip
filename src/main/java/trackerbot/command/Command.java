package trackerbot.command;

import trackerbot.TaskList;
import trackerbot.Ui;
import trackerbot.exception.TrackerBotException;

import java.util.Scanner;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws TrackerBotException;

    public abstract boolean isExit();

    public static Command of(String keyword, String rest) {
        CommandType parsedType = CommandType.UNKNOWN;
        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.getKeyword())) {
                parsedType = command;
                break;
            }
        }

        Command result;
        switch (parsedType) {
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            result = new AddCommand(parsedType, rest);
            break;
        case DELETE:
            result = new DeleteCommand(rest);
            break;
        case MARK:
            // Fallthrough
        case UNMARK:
            result = new ToggleCommand(parsedType, rest);
            break;
        case LIST:
            result = new ListCommand();
            break;
        case BYE:
            result = new ExitCommand();
            break;
        default:
            // this handles the UNKNOWN case
            result = new UnknownCommand();
        }
        return result;
    }

    private static class AddCommand extends Command {
        private final String commandFields;
        private final CommandType type;

        private AddCommand(CommandType type, String commandFields) {
            this.commandFields = commandFields;
            this.type = type;
        }

        public void execute(TaskList tasks, Ui ui) throws TrackerBotException {
            ui.showMessage(tasks.add(type, commandFields));
        }

        public boolean isExit() {
            return false;
        }
    }

    private static class ToggleCommand extends Command {
        private final String commandFields;
        private final CommandType type;

        private ToggleCommand(CommandType type, String commandFields) {
            this.commandFields = commandFields;
            this.type = type;
        }

        public void execute(TaskList tasks, Ui ui) throws TrackerBotException {
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
            switch (type) {
            case MARK:
                ui.showMessage(tasks.markTask(index));
                break;
            case UNMARK:
                ui.showMessage(tasks.unmarkTask(index));
                break;
            default:
                throw new IllegalStateException("Created ToggleCommand with invalid field.");
            }
        }

        public boolean isExit() {
            return false;
        }
    }

    private static class DeleteCommand extends Command {
        private final String commandFields;

        private DeleteCommand(String commandFields) {
            this.commandFields = commandFields;
        }

        public void execute(TaskList tasks, Ui ui) throws TrackerBotException {
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
            ui.showMessage(tasks.delete(index));
        }

        public boolean isExit() {
            return false;
        }
    }

    private static class ExitCommand extends Command {
        private ExitCommand() {}

        public void execute(TaskList tasks, Ui ui) {
            ui.exitApp();
        }

        public boolean isExit() {
            return true;
        }
    }

    private static class ListCommand extends Command {
        private ListCommand() {}

        public void execute(TaskList tasks, Ui ui) {
            ui.showMessage(tasks.list());
        }

        public boolean isExit() {
            return false;
        }
    }

    private static class UnknownCommand extends Command {
        public void execute(TaskList tasks, Ui ui) throws TrackerBotException {
            throw new TrackerBotException("Unrecognised Command Type. Try another?");
        }

        public boolean isExit() {
            return false;
        }
    }
}
