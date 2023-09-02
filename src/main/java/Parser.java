import exception.*;

public class Parser {
    private static TaskList taskList;
    private static Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

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
        case DELETE:
            return new DeleteCommand(taskList, ui);
        case BYE:
            return new ByeCommand(taskList, ui);
        default:
            return new InvalidCommand(taskList, ui);
        }
    }
}
