package duck;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duck.command.AddCommand;
import duck.command.Command;
import duck.command.DeleteCommand;
import duck.command.ExitCommand;
import duck.command.ListCommand;
import duck.command.MarkCommand;
import duck.command.UnmarkCommand;

import duck.task.DeadlineTask;
import duck.task.EventTask;
import duck.task.Task;
import duck.task.TodoTask;

public class Parser {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter fileDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static Command parse(String input) throws DuckException {
        String[] splitInput = input.trim().split(" ", 2);
        String command = splitInput[0].toUpperCase();
        switch (command) {
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        }

        if (splitInput.length < 2) {
            throw new DuckException("Invalid input.");
        }
        String data = splitInput[1];
        switch (command) {
        case "MARK":
            return new MarkCommand(parseIndex(data));
        case "UNMARK":
            return new UnmarkCommand(parseIndex(data));
        case "DELETE":
            return new DeleteCommand(parseIndex(data));
        case "TODO":
            return new AddCommand(parseTodo(data));
        case "DEADLINE":
            return new AddCommand(parseDeadline(data));
        case "EVENT":
            return new AddCommand(parseEvent(data));
        default:
            throw new DuckException("Im sorry, I don't know what that means.");
        }
    }

    private static int parseIndex(String dataString) throws DuckException {
        try {
            int index = Integer.parseInt(dataString);
            return index;
        } catch (NumberFormatException e) {
            throw new DuckException("Please enter a valid task number.");
        }
    }

    private static Task parseTodo(String dataString) throws DuckException {
        try {
            String name = dataString.trim(); 
            return new TodoTask(name, false);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DuckException("The description of a todo cannot be empty.");
        }
    }

    private static DeadlineTask parseDeadline(String dataString) throws DuckException {
        try {
            String[] splitData = dataString.trim().split(" /by ", 2);
            String name = splitData[0].trim();
            LocalDate deadline = LocalDate.parse(splitData[1].trim(), dateFormatter); // potential error if theres no "/by" ???
            return new DeadlineTask(name, false, deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DuckException("Invalid todo task.");
        } catch (DateTimeParseException e) {
            throw new DuckException("Please follow the dd/mm/yyyy format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DuckException("Please follow the /by format.");
        }
    }

    private static EventTask parseEvent(String dataString) throws DuckException {
        try {
            String[] splitData = dataString.trim().split(" /from ", 2);
            String name = splitData[0].trim();
            splitData = splitData[1].trim().split(" /to ", 2);
            LocalDate start = LocalDate.parse(splitData[0].trim(), dateFormatter);
            LocalDate end = LocalDate.parse(splitData[1].trim(), dateFormatter);
            return new EventTask(name, false, start, end);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DuckException("Invalid todo task.");
        } catch (DateTimeParseException e) {
            throw new DuckException("Please follow the dd/mm/yyyy format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DuckException("Please follow the /from and /to format.");
        }
    }

    public static Task parseFromFile(String fileLine) throws DuckException{
        char typeChar = fileLine.charAt(0);

        switch (typeChar) {
        case 'T':
            return TodoTask.parse(fileLine);
        case 'D':
            return DeadlineTask.parse(fileLine);
        case 'E':
            return EventTask.parse(fileLine);
        default:
            throw new DuckException("Invalid file data.");
        }
    }
}
