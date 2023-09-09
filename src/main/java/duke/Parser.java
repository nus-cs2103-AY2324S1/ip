package duke;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.GreetCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

/**
 * Parser reads the primary user input and deals with making sense of the input.
 */
public class Parser {
    private static TaskList taskList;
    private static Ui ui;
    private static Storage storage;

    /**
     * The constructor of Parser.
     *
     * @param taskList The task list to be passed into the Command.
     * @param ui The ui of the chatbot to be passed into the Command.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Returns a specific Command when the corresponding command keywords are
     * detected in the user input.
     *
     * @param fullCommand The full input the user typed into the chatbot.
     * @return A specific Command that corresponds to the command keyword the user inputs.
     */
    public static Command parse(String fullCommand) {
        String command = fullCommand.split(" ")[0];
        Keyword k = Keyword.valueOf(command.toUpperCase());
        switch (k) {
        case HELLO:
            return new GreetCommand(taskList, ui, storage);
        case MARK:
            return new MarkCommand(taskList, ui, storage);
        case UNMARK:
            return new UnmarkCommand(taskList, ui, storage);
        case LIST:
            return new ListCommand(taskList, ui, storage);
        case TODO:
            return new TodoCommand(taskList, ui, storage);
        case DEADLINE:
            return new DeadlineCommand(taskList, ui, storage);
        case EVENT:
            return new EventCommand(taskList, ui, storage);
        case FIND:
            return new FindCommand(taskList, ui, storage);
        case DELETE:
            return new DeleteCommand(taskList, ui, storage);
        case BYE:
            return new ByeCommand(taskList, ui, storage);
        default:
            return new InvalidCommand(taskList, ui, storage);
        }
    }
}
