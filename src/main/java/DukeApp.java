import java.util.HashMap;
import java.util.Objects;

/**
 * The Application object responsible for storing and executing commands.
 */
public class DukeApp {
    /** Default to EchoCommand if input is unknown */
    private static final Command defaultCommand = new EchoCommand();

    /** All commands stored in a map */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public DukeApp() {
        this.addCommand("bye", new ExitCommand());
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param name The name of the command.
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
        Command command = commandMap.get(input);
        Objects.requireNonNullElse(command, defaultCommand).run(input);
    }
}