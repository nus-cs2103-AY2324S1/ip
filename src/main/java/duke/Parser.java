package duke;

import duke.command.*;
import duke.exception.InvalidTaskException;

public class Parser {
    public static Command parse(String fullCommand) {
        //read first word in command
        String[] words = fullCommand.split(" ", 2);
        switch(words[0]) {
        case "bye":
            return new ExitCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "list":
            return new ListCommand(fullCommand);
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "todo":
            return new TodoCommand(fullCommand);
        case "deadline":
            return new DeadlineCommand(fullCommand);
        case "event":
            return new EventCommand(fullCommand);
        default:
            throw new InvalidTaskException();
        }
    }
}
