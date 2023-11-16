package duke.parser;

import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

import duke.exceptions.DukeException;

/**
 * Parses user command and executes tasks based on user commands.
 */
public class Parser {
    private String userCommand;

    /**
     * Constructs new <code>Parser</code> object.
     * @param command the command taken from user input.
     */
    public Parser(String command) {
        this.userCommand = command;
    }

    /**
     * Parses and responds to the user command.
     * @throws DukeException when user command is invalid.
     */
    public String parseAndRespond() throws DukeException {
        String[] splitted = userCommand.split(" ", 2);
        assert splitted != null : "there should be a command";
        String command = splitted[0];
        String response = "Huhhhhhhh??? (o_O) ? "
                + "Please use one of the command words: todo, event, deadline, list, mark, unmark, delete, bye";

        switch (command) {
        case "list":
            return new ListCommand().execute(splitted, response);
        case "mark":
            return new MarkCommand().execute(splitted, response);
        case "unmark":
            return new UnmarkCommand().execute(splitted, response);
        case "find":
            return new FindCommand().execute(splitted, response);
        case "todo":
            return new TodoCommand().execute(splitted, response);
        case "deadline":
            return new DeadlineCommand().execute(splitted, response);
        case "event":
            return new EventCommand().execute(splitted, response);
        case "delete":
            return new DeleteCommand().execute(splitted, response);
        case "bye":
            return "Bye bye! Hope to see you again! o(^w^)o";
        default:
            return response;
        }
    }

}
