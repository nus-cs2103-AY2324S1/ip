package duke;

import duke.command.*;
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

    public Matcher regexParse(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

    /**
     * Parses the instruction that has been typed in by a user, returning the command object to be executed
     * @param response the instruction typed in by the user
     * @return the command object to be executed
     * @throws InvalidTaskException
     */
    public Command parse(String response) throws InvalidTaskException {
        int taskIndex;
        Matcher matcher;

        switch(response.split(" ")[0]) {
            case "list":
                return new PrintListCommand();
            case "mark":
            case "unmark":
                boolean done = response.split(" ")[0].equals("mark") ? true : false;
                taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                return new MarkCommand(done, taskIndex);
            case "todo":
                matcher = this.regexParse("^todo\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 1) {
                    throw new InvalidTaskException("Invalid use of todo. Usage: todo <task description>");
                }
                return new ToDoCommand(matcher.group(1), false);
            case "deadline":
                matcher = regexParse("^deadline\\s([\\w\\s]*)\\s\\/by\\s([\\w\\s\\:\\-]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 2) {
                    throw new InvalidTaskException(
                            "Invalid use of deadline. Usage: deadline <task description> /by <date & time>"
                    );
                }
                return new DeadlineCommand(matcher.group(1), matcher.group(2), false);
            case "event":
                matcher = regexParse("^event\\s([\\w\\s]*)\\s\\/from\\s([\\w\\s\\-\\:]*)\\s\\/to\\s([\\w\\s\\-\\:]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 3) {
                    throw new InvalidTaskException(
                            "Invalid use of event. Usage: event <task description> /from <date & time> /to <date & time>"
                    );
                }
                return new EventCommand(matcher.group(1), matcher.group(2), matcher.group(3), false);
            case "delete":
                matcher = regexParse("^delete\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 1) {
                    throw new InvalidTaskException(
                            "Invalid input. Usage: delete <task_index>"
                    );
                }
                return new DeleteCommand(Integer.parseInt(matcher.group(1)));
            case "save":
                return new SaveCommand(response);
            case "load":
                return new LoadCommand(response);
            case "find":
                matcher = regexParse("^find\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 1) {
                    throw new InvalidTaskException(
                            "Invalid input. Usage: find <description to match>"
                    );
                }
                return new FindCommand(matcher.group(1));
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
    public Task parseSave(String response) throws InvalidFileTypeException {
        boolean done;
        String[] responseList = response.split("\\|");
        Task newtask;

        switch(responseList[0]) {
            case "T":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 3) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Todo needs 3 parameters", response));
                }
                newtask = new ToDo(responseList[2]);
                newtask.setDone(done);
                return newtask;
            case "D":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 4) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Deadline needs 4 parameters", response));
                }
                newtask = new Deadlines(responseList[2], responseList[3]);
                newtask.setDone(done);
                return newtask;
            case "E":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 5) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: duke.task.Event requires 5 parameters", response));
                }
                newtask = new Event(responseList[2], responseList[3], responseList[4]);
                newtask.setDone(done);
                return newtask;
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
    public Date parseDate(String dateTimeString) {
        // YYYY-MM-DD / HH:mm / a combination of both
        String regex = "^(\\d{4}-\\d{2}-\\d{2})? ?(\\d{2}:\\d{2})?$";
        Matcher matcher1 = regexParse(regex, dateTimeString);

        while (matcher1.find()) {
            String datePart = matcher1.group(1);
            String timePart = matcher1.group(2);

            if (datePart != null && timePart != null) {
                System.out.println("duke.Date: " + datePart);
                System.out.println("Time: " + timePart);
            } else if (datePart != null) {
                System.out.println("duke.Date: " + datePart);
            } else if (timePart != null) {
                System.out.println("Time: " + timePart);
            }
            return new Date(datePart, timePart);
        }

        return null;
    }

    public boolean parseSearch(String description, String searchString) {
        String regex = ".*" + searchString + ".*";
        Matcher matcher = regexParse(regex, description);

        return matcher.find();
    }
}
