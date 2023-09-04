package duke.utilities;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.ErrorCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnMarkCommand;

/**
 * A parser than handles user inputs.
 */
public class Parser {
    /**
     * An enum to store the various commands for ease of parsing into the respective commands.
     */
    enum CommandTypes {
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        HELP,
        FIND
    }

    /**
     * The parse function to convert the input, a string, into the respective commands.
     *
     * @param input The string input keyed in by the user.
     * @param taskList The taskList to store the tasks.
     * @param storage The storage to save the new taskList.
     * @param ui The ui that prints the messages.
     * @return the command that was requested by the user.
     */
    public static Command parse(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            String[] inputs = input.split(" ");
            CommandTypes command = CommandTypes.valueOf(inputs[0].toUpperCase());
            switch (command) {
            case BYE:
                return new ByeCommand(taskList, storage, ui);
            case DELETE:
                return new DeleteCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
            case LIST:
                return new ListCommand(taskList, storage, ui);
            case MARK:
                return new MarkCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
            case UNMARK:
                return new UnMarkCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
            case TODO:
                return new TodoCommand(input, taskList, storage, ui);
            case DEADLINE:
                return new DeadlineCommand(input, taskList, storage, ui);
            case EVENT:
                return new EventCommand(input, taskList, storage, ui);
            case HELP:
                return new HelpCommand();
            case FIND:
                return new FindCommand(input, taskList, ui);
            default:
                return new ErrorCommand("I do not understand. Either I am wrong, or you are wrong,"
                        + " so you are wrong", ui);
            }
        } catch (Exception e) {
            return new ErrorCommand("I do not understand. Either I am wrong, or you are wrong, so you are wrong", ui);
        }
    }
}
