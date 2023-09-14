package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.AddTaskExecutable;
import command.ClearExecutable;
import command.DeleteExecutable;
import command.Executable;
import command.FindExecutable;
import command.HelpExecutable;
import command.ListExecutable;
import command.MarkExecutable;
import command.ShutdownExecutable;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * Parses inputs passed via the user interface into commands if possible.
 */

public class Parser {
    private final HashMap<String, ParserFunction> stringToCommand;

    /**
     * Initializes the parser.
     */
    public Parser() {
        stringToCommand = new HashMap<>();
        init();
    }

    /**
     * Initializes the hashmap.
     */
    private void init() {
        stringToCommand.put("bye", Parser::parseShutdownParams);
        stringToCommand.put("help", Parser::parseHelpParams);
        stringToCommand.put("list", Parser::parseListParams);
        stringToCommand.put("clear", Parser::parseClearParams);
        stringToCommand.put("todo", Parser::parseToDoParams);
        stringToCommand.put("deadline", Parser::parseDeadlineParams);
        stringToCommand.put("event", Parser::parseEventParams);
        stringToCommand.put("delete", Parser::parseDeleteParams);
        stringToCommand.put("mark", Parser::parseMarkParams);
        stringToCommand.put("unmark", Parser::parseUnmarkParams);
        stringToCommand.put("find", Parser::parseFindParams);
        //Remember to add an entry into the hashmap whenever a command is added.
    }

    /**
     * Parses the string passed to it and produces the corresponding command.
     * @param input the string passed to the parser that is meant to be interpreted.
     * @return the command that the string represents.
     * @throws InvalidCommandException if the command cannot be identified.
     * @throws InvalidVarException if the command is identifiable but the parameters are incorrect.
     */
    public Executable parse(String input) throws InvalidCommandException, InvalidVarException {
        String commandRegex = "(\\S*)\\s?(.*)";
        Matcher matcher = matchString(input, commandRegex);
        String commandIdentifier = matcher.group(1);
        String paramString = matcher.group(2);
        System.out.println(commandIdentifier + "COMM  \n PARAM" + paramString);
        ParserFunction parsable = stringToCommand.get(commandIdentifier);
        checkIfInvalid(parsable);
        return parsable.apply(paramString);
    }

    private static Matcher matchString(String input, String regex) throws InvalidVarException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidVarException("Incorrect format!");
        }
        return matcher;
    }

    private static boolean parseBoolString(String boolString) throws InvalidVarException {
        if (boolString.equals("TRUE")) {
            return true;
        } else if (boolString.equals("FALSE")) {
            return false;
        } else {
            throw new InvalidVarException("Could not read boolean");
        }
    }

    private static void checkEmpty(String paramString) throws InvalidVarException {
        if (!paramString.isEmpty()) {
            throw new InvalidVarException("Too many parameters!");
        }
    }
    private static void checkNonEmpty(String paramString) throws InvalidVarException {
        if (paramString.isEmpty()) {
            throw new InvalidVarException("No parameters!");
        }
    }
    private static int parseIndex(String paramString) throws InvalidVarException {
        checkNonEmpty(paramString);
        int index;
        try {
            index = Integer.parseInt(paramString);
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        return index;
    }
    private static LocalDate parseLocalDate(String dateString) throws InvalidVarException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidVarException("Could not parse dates!");
        }
    }
    private static Executable parseShutdownParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new ShutdownCommand();
    }
    private static Executable parseHelpParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new HelpCommand();
    }
    private static Executable parseListParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new ListCommand();
    }
    private static Executable parseClearParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new ClearCommand();
    }

    private static Executable parseToDoParams(String paramString) throws InvalidVarException {
        checkNonEmpty(paramString);
        Task todo = new ToDo(paramString);
        return new AddTaskCommand(todo);
    }

    private static Executable parseDeadlineParams(String paramString) throws InvalidVarException {
        String deadlineRegex = "(\\S.*)\\s/by\\s(\\S.*)";
        Matcher matcher = matchString(paramString, deadlineRegex);
        String name = matcher.group(1);
        String deadlineString = matcher.group(2);
        LocalDate deadlineTime = parseLocalDate(deadlineString);
        Deadline deadline = new Deadline(name, deadlineTime);
        return new AddTaskCommand(deadline);
    }
    private static Executable parseEventParams(String paramString) throws InvalidVarException {
        String eventRegex = "(\\S.*)\\s/from\\s(\\S.*)\\s/to\\s(\\S.*)";
        Matcher matcher = matchString(paramString, eventRegex);
        String name = matcher.group(1);
        String startString = matcher.group(2);
        String endString = matcher.group(3);
        LocalDate startTime = parseLocalDate(startString);
        LocalDate endTime = parseLocalDate(endString);
        Event event = new Event(name, startTime, endTime);
        return new AddTaskCommand(event);
    }
    private static Executable parseDeleteParams(String paramString) throws InvalidVarException {
        return new DeleteCommand(parseIndex(paramString));
    }
    private static Executable parseMarkParams(String paramString) throws InvalidVarException {
        return new MarkCommand(true, parseIndex(paramString));
    }
    private static Executable parseUnmarkParams(String paramString) throws InvalidVarException {
        return new MarkCommand(false, parseIndex(paramString));
    }
    private static Executable parseFindParams(String paramString) throws InvalidVarException {
        checkNonEmpty(paramString);
        return new FindCommand(paramString);
    }
    private static void checkIfInvalid(ParserFunction func) throws InvalidCommandException {
        if (func == null) {
            throw new InvalidCommandException("No such command found!");
        }
    }
    private static ToDo todoFromString(String string) throws InvalidVarException {
        String todoRegex = "(.*)" + Task.DIVIDER + "(.*)";
        Matcher matcher = matchString(string, todoRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        return new ToDo (name, isComplete);
    }
    private static Event eventFromString(String string) throws InvalidVarException {
        String eventRegex = "(.*)" + Task.DIVIDER + "(.*)" + Task.DIVIDER +  "(.*)" + Task.DIVIDER +  "(.*)";
        Matcher matcher = matchString(string, eventRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        LocalDate startTime = parseLocalDate(matcher.group(3));
        LocalDate endTime = parseLocalDate(matcher.group(4));
        return new Event (name, isComplete, startTime, endTime);
    }

    private static Deadline deadlineFromString(String string) throws InvalidVarException {
        String deadlineRegex = "(.*)"+ Task.DIVIDER + "(.*)" + Task.DIVIDER + "(.*)";
        Matcher matcher = matchString(string, deadlineRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        LocalDate deadline = parseLocalDate(matcher.group(3));
        return new Deadline (name, isComplete, deadline);
    }
    public static Task taskFromString(String string) throws InvalidVarException {
        String[] temp = string.split(Task.DIVIDER, 1);
        if (temp.length == 1) {
            throw new InvalidVarException();
        }
        String taskIdentifier = temp[0];
        String input = temp[1];
        switch (taskIdentifier) {
            case ("TD"):
                return todoFromString(input);
            case ("DL"):
                return deadlineFromString(input);
            case ("EV"):
                return eventFromString(input);
            default:
                throw new InvalidVarException();
        }
    }
}
