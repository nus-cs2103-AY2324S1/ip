package duke.service;

import java.util.List;

import duke.Duke;
import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindTaskCommand;
import duke.commands.ListCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.UnmarkTaskCommand;
import duke.exception.InvalidCommandInputException;
import duke.exception.UnknownCommandException;

/**
 * A factory class responsible for producing commands based on user inputs.
 * <p>
 * This class interprets the user's command and its associated arguments
 * to generate the correct {@link Command} object that corresponds to the user's request.
 * </p>
 */
public class CommandFactory {
    private TaskFactory taskFactory;
    private Duke dukeBot;
    private UiService uiService;

    /**
     * Initializes a new instance of CommandFactory.
     *
     * @param taskFactory The factory responsible for creating tasks.
     * @param dukeBot     The main Duke bot instance.
     * @param uiService   The UI service instance for user interactions.
     */
    public CommandFactory(TaskFactory taskFactory, Duke dukeBot, UiService uiService) {
        this.taskFactory = taskFactory;
        this.dukeBot = dukeBot;
        this.uiService = uiService;
    }

    /**
     * Creates a {@link Command} object based on the provided command and its arguments.
     *
     * @param command The main user command as a string.
     * @param args    A list of arguments associated with the command.
     * @return The corresponding {@link Command} object.
     * @throws UnknownCommandException     If the provided command string does not match any known command.
     * @throws InvalidCommandInputException If the command input is invalid or incomplete.
     */
    public Command createCommand(String command, List<String> args)
            throws UnknownCommandException, InvalidCommandInputException {
        switch (command) {
        case "bye":
            return new ExitCommand(dukeBot, uiService);
        case "list":
            return new ListCommand(dukeBot, uiService);
        case "mark":
            checkValidTaskNumberArgument(args);
            int markId = Integer.parseInt(args.get(0));
            return new MarkTaskCommand(dukeBot, uiService, markId);
        case "unmark":
            checkValidTaskNumberArgument(args);
            int unmarkId = Integer.parseInt(args.get(0));
            return new UnmarkTaskCommand(dukeBot, uiService, unmarkId);
        case "delete":
            checkValidTaskNumberArgument(args);
            int deleteId = Integer.parseInt(args.get(0));
            return new DeleteTaskCommand(dukeBot, uiService, deleteId);
        case "find":
            if (args.isEmpty()) {
                throw new InvalidCommandInputException("Provide a keyword for find command! :<");
            }
            return new FindTaskCommand(dukeBot, uiService, args.get(0));
        case "todo":
        case "deadline":
        case "event":
            return new AddTaskCommand(dukeBot, uiService, command, args, taskFactory);
        default:
            throw new UnknownCommandException(String.format("Command: %s not recognized! :<", command));
        }
    }

    /**
     * Checks if the provided string is numeric.
     *
     * @param str The string to check.
     * @return {@code true} if the string is numeric, otherwise {@code false}.
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that the given arguments list contains a numeric value as its first item.
     *
     * @param argsList The list of arguments.
     * @throws InvalidCommandInputException If the list is empty or the first item is not numeric.
     */
    private void checkValidTaskNumberArgument(List<String> argsList) throws InvalidCommandInputException {
        if (argsList.isEmpty() || !isNumeric(argsList.get(0))) {
            throw new InvalidCommandInputException("A numeric argument should be provided.");
        }
    }
}
