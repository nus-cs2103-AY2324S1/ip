package duke.parser;

import duke.command.*;
import duke.exception.ChatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private String userInput;
    public Parser(String userInput) {
        this.userInput = userInput;
    }
    public static Command parse(String userInput) throws ChatException {
        String[] userCommand = userInput.split(" ", 2);
        try {
            switch (userCommand[0]) {
                case "list":
                    return new ListCommand();
                case "todo":
                    String todoDescription = userCommand[1];
                    return new TodoCommand(todoDescription);
                case "deadline":
                    try {
                        String[] deadlineTask = userCommand[1].split(" /by ");
                        String deadlineDescription = deadlineTask[0];
                        LocalDate deadlineBy = LocalDate.parse(deadlineTask[1]);
                        return new DeadlineCommand(deadlineDescription, deadlineBy);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ChatException("☹ OOPS!!! Please specify the deadline.");
                    } catch (DateTimeParseException e) {
                        throw new ChatException("☹ OOPS!!! Please specify the deadline in the format of (yyyy-mm-dd).");
                    }
                case "event":
                    try {
                        String[] eventTask = userCommand[1].split(" /from ");
                        String eventDescription = eventTask[0];
                        String[] eventDuration = eventTask[1].split(" /to ");
                        String eventFrom = eventDuration[0];
                        String eventTo = eventDuration[1];
                        return new EventCommand(eventDescription, eventFrom, eventTo);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ChatException("☹ OOPS!!! Please specify the duration.");
                    }
                case "mark":
                    int taskNumberMark = Integer.parseInt(userCommand[1]);
                    return new MarkCommand(taskNumberMark);
                case "unmark":
                    int taskNumberUnmark = Integer.parseInt(userCommand[1]);
                    return new UnmarkCommand(taskNumberUnmark);
                case "delete":
                    int taskNumberDelete = Integer.parseInt(userCommand[1]);
                    return new DeleteCommand(taskNumberDelete);
                case "bye":
                    return new ByeCommand();
                default:
                    return new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            switch (userCommand[0]) {
                case "mark":
                    throw new ChatException("☹ OOPS!!! Please specify the task number.");
                case "unmark":
                    throw new ChatException("☹ OOPS!!! Please specify the task number.");
                case "todo":
                    throw new ChatException("☹ OOPS!!! The description of a todo cannot be empty.");
                case "deadline":
                    throw new ChatException("☹ OOPS!!! The description of a deadline cannot be empty.");
                case "event":
                    throw new ChatException("☹ OOPS!!! The description of an event cannot be empty.");
                case "delete":
                    throw new ChatException("☹ OOPS!!! Please specify the task number.");
                default:
                    throw new ChatException("☹ OOPS!!! Please be more detailed in your instructions.");
            }
        }
    }
}
