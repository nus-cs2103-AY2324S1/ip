package duke;
import duke.command.*;

/**
 * Parses user commands and performs actions based on the parsed command.
 */
public class Parser {

    private enum Commands {
        invalid, todo, deadline, event, mark, unmark, list, delete, find, bye;
    }

    private TaskList taskList;
    private static Ui ui;

    /**
     * Initializes a parser with a user interface and a task list.
     *
     * @param ui       The user interface for displaying messages.
     * @param taskList The task list for managing tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses a user command and performs the corresponding action.
     *
     * @param command The user's input command.
     * @return True if the command was successfully parsed and executed, false otherwise.
     */
    public Command parseCommand(String command) {
        Commands cmd = Commands.invalid;
        for (Commands c : Commands.values()) {
            if (command.startsWith(c.toString())) {
                cmd = c;
            }
        }
        if (cmd.equals(Commands.bye)) {
            return new ByeCommand();
        } else if (cmd.equals(Commands.list)) {
            return new ListCommand();
        } else if (cmd.equals(Commands.mark)) {
            return new MarkCommand(parseTaskIndex(command));
        } else if (cmd.equals(Commands.unmark)) {
            return new UnmarkCommand(parseTaskIndex(command));
        } else if (cmd.equals(Commands.todo)) {
            return new TaskCommand(parseTodo(command));
        } else if (cmd.equals(Commands.deadline)) {
            return new TaskCommand(parseDeadline(command));
        } else if (cmd.equals(Commands.event)) {
            return new TaskCommand(parseEvent(command));
        } else if (cmd.equals(Commands.delete)) {
            return new DeleteCommand(parseTaskIndex(command));
        } else if (cmd.equals(Commands.find)) {
            return new FindCommand(parseKeyword(command));
        } else {
            return new InvalidCommand();
        }
    }

    public static int parseTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public static String parseKeyword(String input) {
        return input.split(" ")[1];
    }

    public static Task parseTodo(String input) {
        String description = input.substring(5);
        Todo todo = new Todo(description);
        return todo;
    }

    public static Task parseDeadline(String input) {
        int index = input.indexOf("/");
        String description = input.substring(9, index - 1);
        String by = input.substring(index + 3);
        Deadline deadline = new Deadline(description, by.trim());
        return deadline;
    }

    public static Task parseEvent(String input) {
        int indexOfFrom = input.indexOf("/");
        String description = input.substring( 6, indexOfFrom - 1);
        String duration = input.substring(indexOfFrom + 4);
        int indexOfTo = duration.indexOf("/");
        String from = duration.substring(1, indexOfTo - 1);
        String to = duration.substring(indexOfTo + 3);
        Event event = new Event(description, from, to);
        return event;
    }
}
