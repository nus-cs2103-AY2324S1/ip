package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;

import duke.exception.KoraException;

/**
 * Parser class process user input and generates command.
 */
public class Parser {

    /**
     * Class constructor of Parser.
     */
    public Parser() {
    }

    /**
     * Processes user input and returns a command based on data processed.
     * @param userInput String input from user.
     * @return Command to be executed.
     * @throws KoraException For invalid input.
     */
    public static Command parse(String userInput) throws KoraException {
        assert !userInput.equals("") : "Input should not be empty.";
        String[] userInputArray = userInput.split("/");
        Command command;
        try {
            if (userInputArray[0].contains("bye")) {
                command = new ByeCommand();
            } else if (userInputArray[0].contains("list")) {
                command = new ListCommand();
            } else if (userInputArray[0].contains("unmark")) {
                command = new UnmarkCommand(userInputArray);
            } else if (userInputArray[0].contains("mark")) {
                command = new MarkCommand(userInputArray);
            } else if (userInputArray[0].contains("deadline")) {
                command = new DeadlineCommand(userInputArray);
            } else if (userInputArray[0].contains("event")) {
                command = new EventCommand(userInputArray);
            } else if (userInputArray[0].contains("todo")) {
                command = new ToDoCommand(userInputArray);
            } else if (userInputArray[0].contains("delete")) {
                command = new DeleteCommand(userInputArray);
            } else if (userInputArray[0].contains("find")) {
                command = new FindCommand(userInputArray);
            } else {
                command = new InvalidCommand();
            }
        } catch (Exception e) {
            throw new KoraException(e.getMessage());
        }
        return command;
    }
}
