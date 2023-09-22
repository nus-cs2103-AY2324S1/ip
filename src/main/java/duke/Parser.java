package duke;
import duke.command.*;
import duke.exception.*;

/**
 * Parses user commands and performs actions based on the parsed command.
 */
public class Parser {

    private enum Commands {
        invalid, todo, deadline, event, mark, unmark, list, delete, find, bye
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
    public Command parseCommand(String command) throws DukeException {
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

    public static int parseTaskIndex(String input) throws DukeInvalidTaskIndexException {
        if (input.split(" ").length < 2) {
            throw new DukeInvalidTaskIndexException("☹ OOPS!!! You must include a task index.");
        }
        assert input.split(" ").length == 2 : "You have entered an invalid task index.";

        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public static String parseKeyword(String input) throws DukeInvalidKeywordException {
        if (input.split(" ").length < 2) {
            throw new DukeInvalidKeywordException("☹ OOPS!!! You must include a keyword.");
        }
        assert input.split(" ").length >= 2 : "You need to include a keyword";

        return input.split(" ")[1];
    }

    public static Task parseTodo(String input) throws DukeInvalidDescriptionException {
        if (input.substring(5).isEmpty()) {
            throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of a todo can't be empty.");
        }
        assert !input.substring(5).isEmpty() : "You need to include a description.";
        String description = input.substring(5);
        
        Todo todo = new Todo(description);
        return todo;
    }

    public static Task parseDeadline(String input) throws DukeInvalidDescriptionException, DukeInvalidDateException {
        if (input.split("/by").length < 2) {
            throw new DukeInvalidDateException("☹ OOPS!!! The date of a deadline can't be empty.");
        }
        assert input.split("/by").length == 2 : "You need to include a date.";
        String by = input.split("/by")[1].trim();

        if (input.split("/by")[0].trim().split(" ").length < 2) {
            throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of a deadline can't be empty.");
        }
        assert input.split("/by")[0].trim().split(" ").length >= 2 : "You need to include a description.";
        String description = input.split("/by")[0].trim().split(" ", 2)[1];

        Deadline deadline = new Deadline(description, by);
        return deadline;
    }

    public static Task parseEvent(String input) throws DukeInvalidDescriptionException, DukeInvalidDurationException {
        if (input.split("/from").length < 2) {
            throw new DukeInvalidDurationException("☹ OOPS!!! The duration of an event can't be empty.");
        }
        assert input.split("/from").length == 2 : "You need to include a duration.";
        String duration = input.split("/from")[1].trim();

        if (duration.split("/to")[0].trim().isEmpty()) {
            throw new DukeInvalidDurationException("☹ OOPS!!! The start of an event can't be empty.");
        }
        assert !duration.split("/to")[0].trim().isEmpty() : "You need to indicate the start of an event.";
        String from = duration.split("/to")[0].trim();

        if (duration.split("/to").length < 2) {
            throw new DukeInvalidDurationException("☹ OOPS!!! The end of an event can't be empty.");
        }
        assert !duration.split("/to")[1].isEmpty() : "You need to indicate the end of an event.";
        String to = duration.split("/to")[1].trim();

        if(input.split("/from")[0].trim().split(" ").length < 2) {
            throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of an event can't be empty.");
        }
        assert input.split("/from")[0].trim().split(" ").length >= 2 : "You need to include a description.";
        String description = input.split("/from")[0].trim().split(" ")[1];

        Event event = new Event(description, from, to);
        return event;
    }
}
