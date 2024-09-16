package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SetPriorityCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownActionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parser class that deals with deciphering user input.
 */
public class Parser {
    /**
     * Parses input given by the user.
     * @param userInput String of input given by the user
     * @return Command instance depending on the action given by the user
     * @throws DukeException Exceptions that will be encountered during parsing
     */
    public static Command parseUserInput(String userInput) throws DukeException {

        assert userInput != null;

        String[] fields = userInput.split("\\s+");
        switch (fields[0]) {
        case "list":
            if (fields.length > 1) {
                throw new InvalidArgumentException("'list'");
            } else {
                return new ListCommand();
            }
        case "find":
            if (fields.length != 2) {
                throw new InvalidArgumentException("'find {keyword}'");
            }
            return new FindCommand(fields[1]);
        case "mark":
            if (fields.length != 2) {
                throw new InvalidArgumentException("'mark {task number}'");
            }
            return new MarkCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "unmark":
            if (fields.length != 2) {
                throw new InvalidArgumentException("'mark {task number}'");
            }
            return new UnmarkCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "delete":
            if (fields.length != 2) {
                throw new InvalidArgumentException("'delete {task number}'");
            }
            return new DeleteCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "priority":
            if (fields.length != 3) {
                throw new InvalidArgumentException("'priority {task number} {priority from 1-3}");
            }
            return new SetPriorityCommand(Character.getNumericValue(fields[1].charAt(0)),
                    Character.getNumericValue(fields[2].charAt(0)));
        case "todo":
            String description = userInput.replace("todo", "").trim();
            if (description.isEmpty()) {
                throw new DukeException("Cannot enter todo with no description");
            }
            Task todo = new ToDo(description);
            return new AddCommand(todo);
        case "deadline":
            fields = userInput.replace("deadline", "").split(" /by ");
            if (fields.length != 2) {
                throw new InvalidArgumentException("'deadline {task description} '/by' {date}'");
            }
            Task deadline = new Deadline(fields[0], fields[1]);
            return new AddCommand(deadline);
        case "event":
            fields = userInput.replace("event", "").split(" /from | /to ");
            if (fields.length != 3) {
                throw new InvalidArgumentException("'event {task description} '/from' {start} '/to' {finish}");
            }
            Task event = new Event(fields[0], fields[1], fields[2]);
            return new AddCommand(event);
        case "bye":
            return new ExitCommand();
        default:
            throw new UnknownActionException();
        }
    }
}
