package rua.common;

import rua.command.*;
import rua.task.Deadline;
import rua.task.Event;
import rua.task.Todo;
import rua.exception.*;

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
        String[] input = message.split(" ", 2);
        String command = input[0];
        Command output;
        switch (command) {
            case "todo":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("Todo");
                }
                Todo newTodo = new Todo(input[1]);
                output = new AddCommand(newTodo);
                break;
            case "deadline":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("Deadline");
                }
                String[] info = input[1].split(" /by ", 2);
                if (info.length == 1) {
                    throw new EmptyDescriptionException("Deadline");
                }
                Deadline newDdl = new Deadline(info[0], LocalDate.parse(info[1]));
                output = new AddCommand(newDdl);
                break;
            case "event":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("Event");
                }
                String[] infoEvent = input[1].split(" /from ", 2);
                if (infoEvent.length == 1) {
                    throw new EmptyDescriptionException("Event");
                }
                String[] duration = infoEvent[1].split(" /to ", 2);
                if (duration.length == 1) {
                    throw  new EmptyDescriptionException("Event");
                }
                Event newEvent = new Event(infoEvent[0], LocalDate.parse(duration[0]), LocalDate.parse(duration[1]));
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
                if (input.length == 1) {
                    throw new EmptyDescriptionException("mark");
                }
                int indexMark = Integer.parseInt(input[1]);
                output = new MarkCommand(indexMark, true);
                break;
            case "unmark":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("unmark");
                }
                int indexUnmark = Integer.parseInt(input[1]);
                output = new MarkCommand(indexUnmark, false);
                break;
            case "delete":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("delete");
                }
                int indexDelete = Integer.parseInt(input[1]);
                output = new DeleteCommand(indexDelete);
                break;
            case "date":
                if (input.length == 1) {
                    throw new EmptyDescriptionException("date search");
                }
                output = new DateSearchCommand(LocalDate.parse(input[1]));
                break;
            default:
                throw new InvalidCommandException();
        }
        return output;
    }
}
