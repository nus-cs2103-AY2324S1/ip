import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;

/**
 * Abstraction for making sense of user commands.
 */
public class Parser {
    public static Command parse(String str) throws InvalidCommandException, InvalidArgumentException {
        if (str.equalsIgnoreCase("bye")) {
            return Command.exit();
        } else if (str.equalsIgnoreCase("list")) {
            return Command.list();
        } else if (str.startsWith("mark ")) {
            return Command.mark(str);
        } else if (str.startsWith("unmark ")) {
            return Command.unmark(str);
        } else if (Task.isTaskCommand(str)) {
            return Command.add(str);
        } else if (str.startsWith("delete ")) {
            return Command.delete(str);
        } else {
            throw new InvalidCommandException();
        }
    }
}
