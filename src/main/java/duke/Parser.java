package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.AddTaskExecutable;
import command.BindingExecutable;
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
    private static final HashMap<String, ParserFunction> stringToCommand = new HashMap<>();
    private final HashMap<String, ParserFunction> customCommand;

    /**
     * Initializes the parser.
     */
    public Parser() {
        this.customCommand = new HashMap<>();
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
        stringToCommand.put("rebind", this::parseRebindParams);
        stringToCommand.put("unbind", this::parseUnbindParams);
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
        ParserFunction parsable = stringToCommand.get(commandIdentifier);
        if (isInvalid(parsable)) {
            parsable = parseCustom(commandIdentifier);
        }
        return parsable.apply(paramString);
    }

    /**
     * Checks the custom command list for the command identifier.
     * @param commandIdentifier the string to be searched for.
     * @return the function mapped to the string if it exists.
     * @throws InvalidCommandException if there is no matching function.
     */
    private ParserFunction parseCustom(String commandIdentifier) throws InvalidCommandException {
        ParserFunction func = this.customCommand.get(commandIdentifier);
        if (isInvalid(func)) {
            throw new InvalidCommandException("Unrecognized command!");
        }
        return func;
    }

    /**
     * Produces a matcher that is guaranteed to be successfully matched.
     * @param input the string to match to a pattern.
     * @param regex the pattern that the input should follow.
     * @return A successful Matcher of the input to the regex.
     * @throws InvalidVarException if the matching was unsuccessful.
     */
    private static Matcher matchString(String input, String regex) throws InvalidVarException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidVarException("Incorrect format!");
        }
        return matcher;
    }

    private static void checkForInvalidDivider(String input) throws InvalidVarException {
        Pattern pattern = Pattern.compile(".*(" + Task.DIVIDER + ").*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            throw new InvalidVarException("Divider detected! Do not use the sequence " + Task.DIVIDER + ".");
        }
    }
    /**
     * Parses a string as a boolean if valid.
     * @param boolString the string to be parsed.
     * @return either true or false, depending on the string.
     * @throws InvalidVarException if the string does not match the format.
     */
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
        return new ShutdownExecutable();
    }
    private static Executable parseHelpParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new HelpExecutable();
    }
    private static Executable parseListParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new ListExecutable();
    }
    private static Executable parseClearParams(String paramString) throws InvalidVarException {
        checkEmpty(paramString);
        return new ClearExecutable();
    }

    private static Executable parseToDoParams(String paramString) throws InvalidVarException {
        checkForInvalidDivider(paramString);
        checkNonEmpty(paramString);
        Task todo = new ToDo(paramString);
        return new AddTaskExecutable(todo);
    }

    private static Executable parseDeadlineParams(String paramString) throws InvalidVarException {
        checkForInvalidDivider(paramString);
        String deadlineRegex = "(\\S.*)\\s/by\\s(\\S.*)";
        Matcher matcher = matchString(paramString, deadlineRegex);
        String name = matcher.group(1);
        String deadlineString = matcher.group(2);
        LocalDate deadlineTime = parseLocalDate(deadlineString);
        Deadline deadline = new Deadline(name, deadlineTime);
        return new AddTaskExecutable(deadline);
    }
    private static Executable parseEventParams(String paramString) throws InvalidVarException {
        checkForInvalidDivider(paramString);
        String eventRegex = "(\\S.*)\\s/from\\s(\\S.*)\\s/to\\s(\\S.*)";
        Matcher matcher = matchString(paramString, eventRegex);
        String name = matcher.group(1);
        String startString = matcher.group(2);
        String endString = matcher.group(3);
        LocalDate startTime = parseLocalDate(startString);
        LocalDate endTime = parseLocalDate(endString);
        ensureChronologicalTimes(startTime, endTime);
        Event event = new Event(name, startTime, endTime);
        return new AddTaskExecutable(event);
    }

    //Following method was added on friend's suggestion to ensure events make sense
    //(rocketninja7 on GitHub).
    private static void ensureChronologicalTimes(LocalDate... dates) throws InvalidVarException {
        if (dates.length == 0 || dates[0] == null) {
            return;
        }
        LocalDate currMax = dates[0];
        for (LocalDate date : dates) {
            if (date.isBefore(currMax)) {
                throw new InvalidVarException("Dates must be in chronological order!");
            } else {
                currMax = date;
            }
        }
    }
    private static Executable parseDeleteParams(String paramString) throws InvalidVarException {
        return new DeleteExecutable(parseIndex(paramString));
    }
    private static Executable parseMarkParams(String paramString) throws InvalidVarException {
        return new MarkExecutable(true, parseIndex(paramString));
    }
    private static Executable parseUnmarkParams(String paramString) throws InvalidVarException {
        return new MarkExecutable(false, parseIndex(paramString));
    }
    private static Executable parseFindParams(String paramString) throws InvalidVarException {
        checkNonEmpty(paramString);
        return new FindExecutable(paramString);
    }
    private Executable parseRebindParams(String paramString) throws InvalidVarException {
        String rebindRegex = "(\\w*)\\s/to\\s(\\w*)";
        Matcher matcher = matchString(paramString, rebindRegex);
        String sourceBinding = matcher.group(1);
        String customBinding = matcher.group(2);
        ParserFunction parserFunc = stringToCommand.get(sourceBinding);
        boolean isValidCommand = (parserFunc != null);
        boolean isBound = ((stringToCommand.get(customBinding) != null) || (customCommand.get(customBinding) != null));
        String errorMessage = "";
        if (!isValidCommand) {
            errorMessage = "Invalid source binding.";
        } else if (isBound) {
            errorMessage = "The custom binding \"" + customBinding + "\" is already bound.";
        } else {
            this.customCommand.put(customBinding, parserFunc);
        }
        return new BindingExecutable(sourceBinding, customBinding, errorMessage);
    }
    private Executable parseUnbindParams(String paramString) throws InvalidVarException {
        String rebindRegex = "(\\w*)";
        Matcher matcher = matchString(paramString, rebindRegex);
        String customBinding = matcher.group(1);
        boolean isCurrentlyBound = (this.customCommand.get(customBinding) != null);
        boolean isNonCustom = (stringToCommand.get(customBinding) != null);
        String errorMessage = "";
        if (isNonCustom) {
            errorMessage = "This binding is not removable!";
        } else if (!isCurrentlyBound) {
            errorMessage = "This custom binding does not exist.";
        } else {
            this.customCommand.remove(customBinding);
        }
        return new BindingExecutable(customBinding, errorMessage);
    }
    private static boolean isInvalid(ParserFunction func) {
        return func == null;
    }
    private static ToDo todoFromString(String string) throws InvalidVarException {
        String todoRegex = "(.*)" + Task.DIVIDER + "(.*)";
        Matcher matcher = matchString(string, todoRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        return new ToDo(name, isComplete);
    }
    private static Event eventFromString(String string) throws InvalidVarException {
        String eventRegex = "(.*)" + Task.DIVIDER + "(.*)" + Task.DIVIDER + "(.*)" + Task.DIVIDER + "(.*)";
        Matcher matcher = matchString(string, eventRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        LocalDate startTime = parseLocalDate(matcher.group(3));
        LocalDate endTime = parseLocalDate(matcher.group(4));
        return new Event(name, isComplete, startTime, endTime);
    }

    private static Deadline deadlineFromString(String string) throws InvalidVarException {
        String deadlineRegex = "(.*)" + Task.DIVIDER + "(.*)" + Task.DIVIDER + "(.*)";
        Matcher matcher = matchString(string, deadlineRegex);
        boolean isComplete = parseBoolString(matcher.group(1));
        String name = matcher.group(2);
        LocalDate deadline = parseLocalDate(matcher.group(3));
        return new Deadline(name, isComplete, deadline);
    }

    /**
     * Produces a task from a string, if the string is valid.
     * @param string the string to be transformed.
     * @return the task representation of the string if valid.
     * @throws InvalidVarException when the string is not in the proper format.
     */
    public static Task taskFromString(String string) throws InvalidVarException {
        String[] temp = string.split(Task.DIVIDER, 2);
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
