package bot.utils;

import bot.exceptions.InvalidArgumentException;
import bot.exceptions.InvalidCommandException;

/**
 * Abstraction for making sense of user commands.
 */
public class Parser {
    /**
     * Default constructor. It does nothing as the parser is not meant to
     * be instantiated.
     */
    public Parser() {}

    /**
     * Parses the command string into a command to be executed.
     * Does not guarantee the command is syntactically correct.
     *
     * @param str Full command string.
     * @return Command object.
     * @throws InvalidCommandException If the command is invalid.
     * @throws InvalidArgumentException If the arguments are clearly invalid.
     */
    public static Command parse(String str) throws InvalidCommandException, InvalidArgumentException {
        if (str.equalsIgnoreCase("bye")) {
            return Command.exit();
        } else if (str.equalsIgnoreCase("list")) {
            return Command.list();
        } else if (str.startsWith("mark ") || str.equals("mark")) {
            return Command.mark(str);
        } else if (str.startsWith("unmark ") || str.equals("unmark")) {
            return Command.unmark(str);
        } else if (Task.isTaskCommand(str)) {
            return Command.add(str);
        } else if (str.startsWith("delete ") || str.equals("delete")) {
            return Command.delete(str);
        } else if (str.startsWith("find ") || str.equals("find")) {
            return Command.find(str);
        } else {
            throw new InvalidCommandException();
        }
    }
}
