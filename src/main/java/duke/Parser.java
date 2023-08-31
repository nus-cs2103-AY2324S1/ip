package duke;

import duke.commands.*;
import duke.exceptions.CommandDetailException;
import duke.exceptions.CommandNotRecognizedException;
import duke.exceptions.TimeParsingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


public class Parser {

    public Parser() {
    }

    public Command parse(String input) throws TimeParsingException, CommandNotRecognizedException, CommandDetailException {
        String trimedInput = input.trim();
        String[] splitInput = trimedInput.split(" ", 2);
        String command = splitInput[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark": {
            int markIndex = Integer.parseInt(splitInput[1]) - 1;
            return new MarkCommand(markIndex);
        }
        case "delete": {
            int deleteIndex = Integer.parseInt(splitInput[1]) - 1;
            return new DeleteCommand(deleteIndex);
        }
        case "unmark": {
            int markIndex = Integer.parseInt(splitInput[1]) - 1;
            return new UnmarkCommand(markIndex);
        }
        default: {
            Task task = parseTask(trimedInput);
            if (task != null) {
                return new AddCommand(task);
            } else {
                throw new CommandNotRecognizedException("I'm sorry, but I don't know what " + input + " means :-(");
            }
        }
        }
    }

    protected Task parseTask(String input) throws TimeParsingException, CommandDetailException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].toLowerCase();

        try {
            switch (command) {
            case "todo":
                return new ToDo(splitInput[1]);
            case "deadline": {
                String[] deadlineParts = splitInput[1].split(" /by ", 2);
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            }
            case "event": {
                String[] eventParts = splitInput[1].split(" /from ", 2);
                String eventName = eventParts[0];
                String[] eventTimes = eventParts[1].split(" /to ", 2);
                return new Event(eventName, eventTimes[0], eventTimes[1]);
            }
            default:
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! The description of a " + command + " cannot be understood.");
        }
    }
}
