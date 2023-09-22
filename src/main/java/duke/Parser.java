package duke;

import duke.command.*;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidFileTypeException;
import duke.exceptions.InvalidTaskException;
import duke.task.Deadlines;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used for any parsing of user input
 */
public class Parser {
    public Parser() {}

    public Matcher regexParse(String regex, String response) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(response);
    }

    /**
     * Parses the instruction that has been typed in by a user, returning the command object to be executed
     * @param response the instruction typed in by the user
     * @return the command object to be executed
     * @throws InvalidTaskException
     */
    public Command parse(String response) {
        switch(response.split(" ")[0]) {
        case "list":
            return new PrintListCommand();
        case "mark":
        case "unmark":
            return new MarkCommand(response);
        case "todo":
            return new ToDoCommand(response);
        case "deadline":
            return new DeadlineCommand(response);
        case "event":
            return new EventCommand(response);
        case "delete":
            return new DeleteCommand(response);
        case "save":
            return new SaveCommand(response);
        case "load":
            return new LoadCommand(response);
        case "find":
            return new FindCommand(response);
        case "bye":
            return new ByeCommand();
        default: {
            return new DefaultCommand(response);
        }
        }
    }

    /**
     * Parses the lines of the save file and returns a Task to be added to the TaskList
     * @param response line of the save file to be parsed
     * @return A task generated from the saved line
     * @throws InvalidFileTypeException
     */
    public Task parseSave(String response) throws InvalidFileTypeException, InvalidDateTimeFormatException {
        boolean done;
        String[] responseList = response.split("\\|");
        Task newTask;

        switch(responseList[0]) {
            case "T":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 3) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Todo needs 3 parameters", response));
                }
                newTask = new ToDo(responseList[2]);
                newTask.setDone(done);
                return newTask;
            case "D":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 4) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Deadline needs 4 parameters", response));
                }
                newTask = new Deadlines(responseList[2], responseList[3]);
                newTask.setDone(done);
                return newTask;
            case "E":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 5) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: duke.task.Event requires 5 parameters", response));
                }
                newTask = new Event(responseList[2], responseList[3], responseList[4]);
                newTask.setDone(done);
                return newTask;
            default: {
                throw new InvalidFileTypeException(String.format("line: %s is invalid: duke.task.Event requires 5 parameters", response));
            }
        }
    }

    /**
     * Parses a datetime string to make sure that it complies with the specified format
     * @param dateTimeString the datetime string to be processed
     * @return the Date object generated
     */
    public Date parseDate(String dateTimeString) throws InvalidDateTimeFormatException {
        // YYYY-MM-DD / HH:mm / a combination of both
        String regex = "^(\\d{4}-\\d{2}-\\d{2})? ?(\\d{2}:\\d{2})?$";
        Matcher matcher1 = regexParse(regex, dateTimeString);

        while (matcher1.find()) {
            String datePart = matcher1.group(1);
            String timePart = matcher1.group(2);
            return new Date(datePart, timePart);
        }

        throw new InvalidDateTimeFormatException("Invalid date time format: YYYY-MM-DD / HH:mm / a combination of both");
    }

    public boolean parseSearch(String description, String searchString) {
        String regex = ".*" + searchString + ".*";
        Matcher matcher = regexParse(regex, description);

        return matcher.find();
    }
}
