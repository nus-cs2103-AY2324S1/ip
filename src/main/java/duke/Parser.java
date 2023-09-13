package duke;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SortCommand;
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
     * @param input The full input the user typed into the chatbot.
     * @return A specific Command that corresponds to the command keyword the user inputs.
     */
    public static Command parse(String input) {
        assert input != null : "There should be an input";
        String command = input.split(" ")[0];
        Keyword keyword = Keyword.valueOf(command.toUpperCase());
        assert keyword != null : "There should be a command";
        switch (keyword) {
        case MARK:
            return new MarkCommand();
        case UNMARK:
            return new UnmarkCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return new TodoCommand();
        case DEADLINE:
            return new DeadlineCommand();
        case EVENT:
            return new EventCommand();
        case FIND:
            return new FindCommand();
        case SORT:
            return new SortCommand();
        case DELETE:
            return new DeleteCommand();
        case BYE:
            return new ByeCommand();
        default:
            return new InvalidCommand();
        }
    }
}
