package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

/**
 * Abstracts the Commands obtained from user input.
 * <p> Command nests its child classes inside its abstraction, as there is
 * a small number of available commands that can be called through the UI. </p>
 * <p>Every nested Command should be assigned a corresponding CommandType enum.</p>
 * @author WZWren
 * @version A-Assertions
 */
public abstract class Command {
    /**
     * Runs the command specified by the specific command.
     *
     * @param tasks The Collection of Tasks stored by TrackerBot.
     * @param uiHandler The UI object of TrackerBot, to pass status messages into.
     */
    public abstract void execute(TaskList tasks, UiHandler uiHandler);

    /**
     * Factory method for Command.
     * <p>Depending on the keyword passed in, the method will generate an
     * appropriate instance of a subtype of Command. Currently, this method can generate
     * these subtypes:</p>
     * <ul>
     *     <li>AddCommand, to add Tasks into the Tracker.</li>
     *     <li>DeleteCommand, to remove Tasks from the Tracker.</li>
     *     <li>ToggleCommand, to mark/unmark Tasks.</li>
     *     <li>FindCommand, to find all Tasks matching a substring.</li>
     *     <li>ListCommand, to display Tasks in the Tracker.</li>
     *     <li>ExitCommand, to tell the Tracker to exit.</li>
     *     <li>UnknownCommand, which will throw an error on execute.</li>
     * </ul>
     * @param keyword The keyword passed in by the user input.
     * @param commandField The description of the user input.
     * @return Some subtype of Command related to keyword.
     */
    public static Command of(String keyword, String commandField) {
        CommandType parsedType = getCommandType(keyword);

        Command result;
        switch (parsedType) {
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            result = new AddCommand(parsedType, commandField);
            break;
        case DELETE:
            result = new DeleteCommand(commandField);
            break;
        case MARK:
            // Fallthrough
        case UNMARK:
            result = new ToggleCommand(parsedType, commandField);
            break;
        case FIND:
            result = new FindCommand(commandField);
            break;
        case LIST:
            result = new ListCommand();
            break;
        case BYE:
            result = new ExitCommand();
            break;
        default:
            result = new UnknownCommand();
        }
        return result;
    }

    private static CommandType getCommandType(String keyword) {
        CommandType result = CommandType.UNKNOWN;
        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.getKeyword())) {
                result = command;
                break;
            }
        }
        return result;
    }
}
