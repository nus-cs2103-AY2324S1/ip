package dukeapp;

import dukeapp.commands.Command;
import dukeapp.commands.DeleteCommand;
import dukeapp.commands.ExitCommand;
import dukeapp.commands.InsertCommand;
import dukeapp.commands.ListCommand;
import dukeapp.commands.MarkCommand;
import dukeapp.commands.UnmarkCommand;

import dukeapp.exceptions.UnknownCommandException;

import java.util.HashMap;

/**
 * Represents the Application object responsible for storing and executing
 * commands.
 */
public class DukeApp {
    /**
     * All commands stored in a map.
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public DukeApp() {
        DukeState state = new DukeState();
        this.addCommand("bye", new ExitCommand());

        this.addCommand("delete", new DeleteCommand(state));
        this.addCommand("list", new ListCommand(state));

        this.addCommand("mark", new MarkCommand(state));
        this.addCommand("unmark", new UnmarkCommand(state));

        this.addCommand("deadline", new InsertCommand(state));
        this.addCommand("event", new InsertCommand(state));
        this.addCommand("todo", new InsertCommand(state));
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param name    The name of the command.
     * @param command The associated command.
     */
    public void addCommand(String name, Command command) {
        this.commandMap.put(name, command);
    }

    /**
     * Executes a command given an input.
     *
     * @param input The input of the user.
     * @throws UnknownCommandException If no known command can be found from
     *                                 the input.
     */
    public void executeCommand(String input) throws UnknownCommandException {
        System.out.println("\t" + DukeConstants.HORIZONTAL_LINE);

        // Separate the command name and the command input
        String[] args = input.split(" ");
        String commandName = args[0];

        Command command = commandMap.get(commandName);
        if (command != null) {
            command.run(input);
        } else {
            throw new UnknownCommandException(
                    DukeConstants.UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }
}