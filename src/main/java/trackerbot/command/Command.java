package trackerbot.command;

import trackerbot.task.TaskList;
import trackerbot.utils.Ui;
import trackerbot.exception.TrackerBotException;

import java.util.Scanner;

/**
 * Abstracts the Commands obtained from user input.
 * <p> Command nests its child classes inside its abstraction, as there is
 * a small number of available commands that can be called through the UI. </p>
 */
public abstract class Command {
    /**
     * Runs the command specified by the specific command.
     *
     * @param tasks The Collection of Tasks stored by TrackerBot.
     * @param ui The UI object of TrackerBot, to pass status messages into.
     * @throws TrackerBotException The internal TrackerBotException, for expected and recoverable
     *                             errors to display on the UI.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws TrackerBotException;

    public abstract boolean isExit();

    /**
     * Factory method for Command.
     * <p>Depending on the keyword passed in, the method will generate an
     * appropriate instance of a subtype of Command. Currently, this method can generate
     * these subtypes:</p>
     * <ul>
     *     <li>AddCommand, to add Tasks into the Tracker.</li>
     *     <li>DeleteCommand, to remove Tasks from the Tracker.</li>
     *     <li>ToggleCommand, to mark/unmark Tasks.</li>
     *     <li>ListCommand, to display Tasks in the Tracker.</li>
     *     <li>ExitCommand, to tell the Tracker to exit.</li>
     *     <li>UnknownCommand, which will throw an error on execute.</li>
     * </ul>
     * @param keyword The keyword passed in by the user input.
     * @param rest The description of the user input.
     * @return Some subtype of Command related to keyword.
     */
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
