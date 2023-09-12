package rua.common;

import java.time.LocalDate;

import rua.command.AddCommand;
import rua.command.ClearCommand;
import rua.command.DateSearchCommand;
import rua.command.DeleteCommand;
import rua.command.ExitCommand;
import rua.command.ListCommand;
import rua.command.MarkCommand;
import rua.command.Command;
import rua.command.SearchCommand;
import rua.task.Deadline;
import rua.task.Event;
import rua.exception.EmptyDescriptionException;
import rua.exception.InvalidCommandException;
import rua.task.Todo;



public class Parser {
    /**
     * Translates the input message into a Command object.
     *
     * @param message The String input from the user.
     * @return The corresponding Command object.
     * @throws EmptyDescriptionException if there is no description for the string representing the input task.
     * @throws InvalidCommandException if the message is not supported.
     */
    public static Command parse(String message) throws EmptyDescriptionException, InvalidCommandException {
        String[] inputs = message.split(" ", 2);
        String command = inputs[0];
        Command output;
        switch (command) {
        case "todo":
            output = createAddTodoCommand(inputs);
            break;
        case "deadline":
            output = createAddDeadlineCommand(inputs);
            break;
        case "event":
            output = createAddEventCommand(inputs);
            break;
        case "bye":
            output = new ExitCommand();
            break;
        case "list":
            output = new ListCommand();
            break;
        case "clear":
            output = new ClearCommand();
            break;
        case "mark":
            output = createMarkEventCommand(inputs, true);
            break;
        case "unmark":
            output = createMarkEventCommand(inputs, false);
            break;
        case "delete":
            checkEmptyDescription(inputs, "Delete");
            int indexDelete = Integer.parseInt(inputs[1]);
            output = new DeleteCommand(indexDelete);
            break;
        case "date":
            checkEmptyDescription(inputs, "Date Search");
            output = new DateSearchCommand(LocalDate.parse(inputs[1]));
            break;
        case "find":
            checkEmptyDescription(inputs, "Search Task");
            output = new SearchCommand(inputs[1]);
            break;
        default:
            throw new InvalidCommandException();
        }
        return output;
    }

    private static void checkEmptyDescription(String[] inputs, String commandType)
            throws EmptyDescriptionException{
        if (inputs.length == 1) {
            throw new EmptyDescriptionException(commandType);
        }
    }

    private static AddCommand createAddTodoCommand(String[] inputs) throws EmptyDescriptionException{
        checkEmptyDescription(inputs, "Todo");
        Todo newTodo = new Todo(inputs[1]);
        return new AddCommand(newTodo);
    }

    private static AddCommand createAddDeadlineCommand(String[] inputs)
            throws EmptyDescriptionException{
        checkEmptyDescription(inputs, "Deadline");
        String[] infos = inputs[1].split(" /by ", 2);
        checkEmptyDescription(infos, "Deadline");
        final LocalDate dueDate = LocalDate.parse(infos[1]);
        Deadline newDdl = new Deadline(infos[0], dueDate);
        return new AddCommand(newDdl);
    }

    private static AddCommand createAddEventCommand(String[] inputs)
            throws EmptyDescriptionException{
        checkEmptyDescription(inputs, "Event");
        String[] infosEvent = inputs[1].split(" /from ", 2);
        checkEmptyDescription(infosEvent, "Event");
        String[] durations = infosEvent[1].split(" /to ", 2);
        checkEmptyDescription(durations, "Event");
        final LocalDate fromDate = LocalDate.parse(durations[0]);
        final LocalDate toDate = LocalDate.parse(durations[1]);
        Event newEvent = new Event(infosEvent[0], fromDate, toDate);
        return new AddCommand(newEvent);
    }

    private static MarkCommand createMarkEventCommand(String[] inputs, Boolean willMark)
            throws EmptyDescriptionException{
        checkEmptyDescription(inputs, willMark ? "mark" : "unmark");
        int indexMark = Integer.parseInt(inputs[1]);
        return new MarkCommand(indexMark, willMark);
    }
}


