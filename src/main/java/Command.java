import ip.utils.Pair;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws TrackerBotException;

    public Command of(String keyword, String rest) {
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
            result = new ListCommand(rest);
            break;
        case BYE:
            result = new ExitCommand(rest);
            break;
        default:
            // this handles the UNKNOWN case
            result = new UnknownCommand();
        }
        return result;
    }

    private class AddCommand extends Command {
        private final String commandFields;
        private final CommandType type;

        private AddCommand(CommandType type, String commandFields) {
            this.commandFields = commandFields;
            this.type = type;
        }

        public void execute(TaskList tasks, Ui ui) {

        }
    }

    private class ToggleCommand extends Command {
        private final String commandFields;
        private final CommandType type;

        private ToggleCommand(CommandType type, String commandFields) {
            this.commandFields = commandFields;
            this.type = type;
        }

        public void execute(TaskList tasks, Ui ui) {

        }
    }

    private class DeleteCommand extends Command {
        private final String commandFields;

        private DeleteCommand(String commandFields) {
            this.commandFields = commandFields;
        }

        public void execute(TaskList tasks, Ui ui) {

        }
    }

    private class ExitCommand extends Command {
        private final String commandFields;

        private ExitCommand(String commandFields) {
            this.commandFields = commandFields;
        }

        public void execute(TaskList tasks, Ui ui) {

        }
    }

    private class ListCommand extends Command {
        private final String commandFields;

        private ListCommand(String commandFields) {
            this.commandFields = commandFields;
        }

        public void execute(TaskList tasks, Ui ui) {

        }
    }

    private class UnknownCommand extends Command {
        public void execute(TaskList tasks, Ui ui) throws TrackerBotException {
            throw new TrackerBotException("Unrecognised Command Type.");
        }
    }
}
