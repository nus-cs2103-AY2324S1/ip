package duke.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.commands.Command;
import duke.exception.InvalidCommandInputException;
import duke.exception.UnknownCommandException;

/**
 * Provides functionality to parse and execute commands from the command line interface.
 * <p>
 * This service is responsible for continuously reading commands from the CLI, parsing them into
 * their respective components, and then executing the associated commands using a {@link CommandFactory}.
 * </p>
 */
public class CliParserService {
    private final UiService uiService;
    private final CommandFactory commandFactory;

    /**
     * Constructs a new instance of the CLI parser service.
     *
     * @param uiService       The UI service to use for interactions with the user.
     * @param commandFactory  The command factory to use for creating command objects.
     */
    public CliParserService(UiService uiService, CommandFactory commandFactory) {
        this.uiService = uiService;
        this.commandFactory = commandFactory;
    }

    /**
     * Reads and executes commands from the command line interface continuously.
     * <p>
     * This method will repeatedly read lines of input from the CLI, parse the lines into command
     * components, create the associated {@link Command} objects using the {@link CommandFactory},
     * and then execute the commands. If an "exit" command is encountered, this method will return.
     * </p>
     */
    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            ParseResult parseResult = parseCommandAndArguments(line);
            String commandType = parseResult.getCommandType();
            List<String> arguments = parseResult.getArguments();
            try {
                Command command = commandFactory.createCommand(commandType, arguments);
                if (command.isExit()) {
                    return;
                }
                command.execute();
            } catch (UnknownCommandException | InvalidCommandInputException e) {
                uiService.printGenericMessage(e.getMessage());
            }
        }
    }

    /**
     * Parses a command line into its main command and associated arguments.
     *
     * @param line The command line to parse.
     * @return A {@link ParseResult} object containing the parsed command and its arguments.
     */
    public ParseResult parseCommandAndArguments(String line) {
        line = line.trim();
        List<String> arguments = new ArrayList<>();

        // Split the command and its primary argument
        String[] splitBySpace = line.split(" ", 2);
        String commandType = splitBySpace[0].toLowerCase();

        // If there's more than just the command, handle the arguments
        if (splitBySpace.length > 1) {
            // primaryArg refers to either task name, or the task index.
            String primaryArg = splitBySpace[1].split("/")[0].trim();
            arguments.add(primaryArg);

            // Split the rest by slashes
            String[] splitBySlash = splitBySpace[1].split("/");
            for (int i = 1; i < splitBySlash.length; ++i) { // Start at 1 to skip the task name
                arguments.add(splitBySlash[i].trim());
            }
        }
        return new ParseResult(commandType, arguments);
    }

    /**
     * Represents the result of parsing a command line into its main command and arguments.
     */
    static final class ParseResult {
        private String commandType;
        private List<String> arguments;

        /**
         * Constructs a new parse result.
         *
         * @param commandType The parsed command type.
         * @param arguments   The list of parsed arguments.
         */
        ParseResult(String commandType, List<String> arguments) {
            this.commandType = commandType;
            this.arguments = arguments;
        }

        /**
         * Retrieves the parsed command type.
         *
         * @return The command type as a string.
         */
        public String getCommandType() {
            return commandType;
        }

        /**
         * Retrieves the list of parsed arguments.
         *
         * @return A list of arguments.
         */
        public List<String> getArguments() {
            return arguments;
        }
    }
}
