package duke;

import command.*;

/**
 * Parser reads the primary user input and deals with making sense of the input.
 */
public class Parser {
    private static TaskList taskList;
    private static Ui ui;

    /**
     * The constructor of Parser.
     *
     * @param taskList The task list to be passed into the Command.
     * @param ui The ui of the chatbot to be passed into the Command.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
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
        case MARK:
            return new MarkCommand(taskList, ui);
        case UNMARK:
            return new UnmarkCommand(taskList, ui);
        case LIST:
            return new ListCommand(taskList, ui);
        case TODO:
            return new TodoCommand(taskList, ui);
        case DEADLINE:
            return new DeadlineCommand(taskList, ui);
        case EVENT:
            return new EventCommand(taskList, ui);
        case FIND:
            return new FindCommand(taskList, ui);
        case DELETE:
            return new DeleteCommand(taskList, ui);
        case BYE:
            return new ByeCommand(taskList, ui);
        default:
            return new InvalidCommand(taskList, ui);
        }
    }
}
