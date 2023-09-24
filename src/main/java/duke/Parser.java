package duke;
import duke.command.*;
import duke.exception.*;

/**
 * Parses user commands and performs actions based on the parsed command.
 */
public class Parser {

    private enum Commands {
        invalid, todo, deadline, event, mark, unmark, list, delete, find, bye, edit
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
     * @return A command object if the user input is parsed successfully.
     * @throws DukeException If an error occurs while processing the input.
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
        } else if (cmd.equals(Commands.edit)) {
            return new EditCommand(parseTaskIndex(command), parseEdit(command));
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Parses the task index from the user's input command.
     *
     * @param input The user's input command.
     * @return The parsed task index.
     * @throws DukeInvalidTaskIndexException If the task index is missing or invalid.
     */
    public static int parseTaskIndex(String input) throws DukeInvalidTaskIndexException {
        if (input.split(" ").length < 2) {
            throw new DukeInvalidTaskIndexException("☹ OOPS!!! You must include a task index.");
        }
        assert input.split(" ").length >= 2 : "You have entered an invalid task index.";

        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Parses the keyword from the user's input command.
     *
     * @param input The user's input command.
     * @return The parsed keyword.
     * @throws DukeInvalidKeywordException If the keyword is missing.
     */
    public static String parseKeyword(String input) throws DukeInvalidKeywordException {
        if (input.split(" ").length < 2) {
            throw new DukeInvalidKeywordException("☹ OOPS!!! You must include a keyword.");
        }
        assert input.split(" ").length >= 2 : "You need to include a keyword";

        return input.split(" ")[1];
    }

    /**
     * Parses a Todo from the user's input command.
     *
     * @param input The user's input command.
     * @return The parsed Todo task.
     * @throws DukeInvalidDescriptionException If the description is missing or empty.
     */
    public static Todo parseTodo(String input) throws DukeInvalidDescriptionException {
        if (input.substring(5).isEmpty()) {
            throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of a todo can't be empty.");
        }
        assert !input.substring(5).isEmpty() : "You need to include a description.";
        String description = input.substring(5);

        return new Todo(description);
    }

    /**
     * Parses a Deadline from the user's input command.
     *
     * @param input The user's input command.
     * @return The parsed Deadline task.
     * @throws DukeInvalidDescriptionException If the description is missing or empty.
     * @throws DukeInvalidDateException If the date is missing.
     */
    public static Deadline parseDeadline(String input) throws DukeInvalidDescriptionException, DukeInvalidDateException {
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

        return new Deadline(description, by);
    }

    /**
     * Parses an Event from the user's input command.
     *
     * @param input The user's input command.
     * @return The parsed Event task.
     * @throws DukeInvalidDescriptionException If the description is missing or empty.
     * @throws DukeInvalidDurationException If the duration is missing or empty.
     */
    public static Event parseEvent(String input) throws DukeInvalidDescriptionException, DukeInvalidDurationException {
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

        return new Event(description, from, to);
    }

    /**
     * Parses a description from the user's input command.
     *
     * @param input The user's input command.
     * @return The new description for the task.
     * @throws DukeInvalidDescriptionException If the description is missing or empty.
     */
    public static String parseEdit(String input) throws DukeInvalidDescriptionException {
        if (input.split("/to").length < 2) {
            throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of your edit can't be empty.");
        }
        assert input.split("/to").length == 2 : "You need to include a description";

        return input.split("/to")[1].trim();
    }
}
