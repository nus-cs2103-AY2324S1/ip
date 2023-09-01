package rua.common;

import rua.command.AddCommand;
import rua.command.ClearCommand;
import rua.command.DateSearchCommand;
import rua.command.DeleteCommand;
import rua.command.ExitCommand;
import rua.command.ListCommand;
import rua.command.MarkCommand;
import rua.command.SearchCommand;
import rua.command.Command;
import rua.task.Deadline;
import rua.task.Event;
import rua.task.Todo;
import rua.exception.EmptyDescriptionException;
import rua.exception.InvalidCommandException;

import java.time.LocalDate;

public class Parser {
    /**
     * Translates the input message into a Command object.
     *
     * @param message The String input from the user.
     * @return The corresponding Command object.
     * @throws EmptyDescriptionException if there is no description for the string representing the input task.
     * @throws InvalidCommandException if the message is not supported.
     */
    static public Command parse(String message) throws EmptyDescriptionException, InvalidCommandException {
        String[] inputs = message.split(" ", 2);
        String command = inputs[0];
        Command output;
        switch (command) {
        case "todo":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("Todo");
            }
            Todo newTodo = new Todo(inputs[1]);
            output = new AddCommand(newTodo);
            break;
        case "deadline":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("Deadline");
            }
            String[] infos = inputs[1].split(" /by ", 2);
            if (infos.length == 1) {
                throw new EmptyDescriptionException("Deadline");
            }
            Deadline newDdl = new Deadline(infos[0], LocalDate.parse(infos[1]));
            output = new AddCommand(newDdl);
            break;
        case "event":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("Event");
            }
            String[] infosEvent = inputs[1].split(" /from ", 2);
            if (infosEvent.length == 1) {
                throw new EmptyDescriptionException("Event");
            }
            String[] durations = infosEvent[1].split(" /to ", 2);
            if (durations.length == 1) {
                throw new EmptyDescriptionException("Event");
            }
            Event newEvent = new Event(infosEvent[0], LocalDate.parse(durations[0]), LocalDate.parse(durations[1]));
            output = new AddCommand(newEvent);
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
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("mark");
            }
            int indexMark = Integer.parseInt(inputs[1]);
            output = new MarkCommand(indexMark, true);
            break;
        case "unmark":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("unmark");
            }
            int indexUnmark = Integer.parseInt(inputs[1]);
            output = new MarkCommand(indexUnmark, false);
            break;
        case "delete":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("delete");
            }
            int indexDelete = Integer.parseInt(inputs[1]);
            output = new DeleteCommand(indexDelete);
            break;
        case "date":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("date search");
            }
            output = new DateSearchCommand(LocalDate.parse(inputs[1]));
            break;
        case "find":
            if (inputs.length == 1) {
                throw new EmptyDescriptionException("keyword search");
            }
            output = new SearchCommand(inputs[1]);
            break;
        default:
            throw new InvalidCommandException();
        }
        return output;
    }
}
