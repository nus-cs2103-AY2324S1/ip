import java.util.HashMap;

/**
 * The Application object responsible for storing and executing commands.
 */
public class DukeApp {
    /**
     * Default to EchoCommand if input is unknown.
     */
    private final Command defaultCommand;

    /**
     * All commands stored in a map.
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public DukeApp() {
        DukeState state = new DukeState();
        this.addCommand("bye", new ExitCommand());
        this.addCommand("list", new ListCommand(state));
        this.addCommand("mark", new MarkCommand(state));
        this.addCommand("unmark", new UnmarkCommand(state));
        this.defaultCommand = new InsertCommand(state);
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
     */
    public void executeCommand(String input) {
        System.out.println("\t" + DukeConstants.HORIZONTAL_LINE);

        // Separate the command name and the command input
        String[] args = input.split(" ", 2);
        String commandName = args[0];
        String commandInput = args.length > 1 ? args[1] : "";

        Command command = commandMap.get(commandName);
        if (command != null) {
            command.run(commandInput);
        } else {
            // No known command name given, use whole input to create task
            this.defaultCommand.run(input);
        }
    }
}