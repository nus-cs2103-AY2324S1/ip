package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownActionException;

import duke.command.*;

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
        String[] fields = userInput.split("\\s+");
        switch (fields[0]) {
        case "list":
            if (fields.length > 1) {
                throw new InvalidArgumentException("Please enter 'list' without any extra arguments " +
                        "or use a different " +
                        "keyword");
            } else {
                return new ListCommand();
            }
        case "mark":
            if (fields.length != 2) {
                throw new InvalidArgumentException("Please enter 'mark {task number}' or use a different " +
                        "keyword");
            }
            return new MarkCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "unmark":
            if (fields.length != 2) {
                throw new InvalidArgumentException("Please enter 'mark {task number}' or use a different " +
                        "keyword");
            }
            return new UnmarkCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "delete":
            if (fields.length != 2) {
                throw new InvalidArgumentException("Please enter 'delete {task number}' " +
                        "or use a different " +
                        "keyword");
            }
            return new DeleteCommand(Character.getNumericValue(fields[1].charAt(0)));
        case "todo":
            String description = userInput.replace("todo", "");
            Task todo = new ToDo(description);
            return new AddCommand(todo);
        case "deadline":
            fields = userInput.replace("deadline", "").split(" /by ");
            if (fields.length != 2) {
                throw new InvalidArgumentException("Please enter 'deadline {task description} " +
                        "'/by' {date}' or use a different " +
                        "keyword");
            }
            Task deadline = new Deadline(fields[0], fields[1]);
            return new AddCommand(deadline);
        case "event":
            fields = userInput.replace("event", "").split(" /from | /to ");
            if (fields.length != 3) {
                throw new InvalidArgumentException("Please enter 'event {task description} " +
                        "'/from' {start} '/to' {finish} " +
                        "or use a different " +
                        "keyword");
            }
            Task event = new Event(fields[0], fields[1], fields[2]);
            return new AddCommand(event);
        case "bye":
            return new ExitCommand();
        default:
            throw new UnknownActionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
