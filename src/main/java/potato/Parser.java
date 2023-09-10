package potato;

import potato.command.*;
public class Parser {
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);

        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);

        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);

        } else if (input.startsWith("find ")) {
            return new FindCommand(input);

        } else if (input.equals("list")) {
            return new ListCommand();

        } else {
            return new AddCommand(input);
        }
    }
}
