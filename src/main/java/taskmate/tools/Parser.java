package taskmate.tools;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import taskmate.commands.Command;
import taskmate.commands.DeadlineCommand;
import taskmate.commands.DeleteCommand;
import taskmate.commands.EventCommand;
import taskmate.commands.ExitCommand;
import taskmate.commands.FindCommand;
import taskmate.commands.HelpCommand;
import taskmate.commands.ListCommand;
import taskmate.commands.MarkCommand;
import taskmate.commands.TodoCommand;
import taskmate.commands.UnmarkCommand;
import taskmate.commands.UpdateCommand;
import taskmate.exceptions.ClauselessUpdateException;
import taskmate.exceptions.EmptyByException;
import taskmate.exceptions.EmptyDescriptionException;
import taskmate.exceptions.EmptyFromException;
import taskmate.exceptions.EmptyToException;
import taskmate.exceptions.InvalidByException;
import taskmate.exceptions.InvalidCommandTypeException;
import taskmate.exceptions.InvalidFromException;
import taskmate.exceptions.InvalidToException;
import taskmate.exceptions.NotAnIntegerException;
import taskmate.main.TaskMate;

/**
 * This Parser class contains static methods that parse the user's input and maps it to Command objects, if possible.
 * Otherwise, in the case of invalid inputs, exceptions are thrown.
 */
public class Parser {

    /**
     * Parses the raw user input and returns a Command object that represents the input.
     * @param userInput A String object passed by the user representing their command to the chatbot
     * @return A Command object that represents the String command passed by the user
     * @throws InvalidCommandTypeException Thrown when the user passes an unrecognized command
     * @throws EmptyDescriptionException Thrown when the user does not provide a description for the command
     * @throws EmptyByException Thrown when the user attempts to create a DeadlineCommand but does not specify the "by"
     *                          clause
     * @throws InvalidByException Thrown when the user attempts to create a DeadlineCommand but provides a "by" clause
     *                          with incorrect formatting
     * @throws InvalidToException Thrown when the user attempts to create a EventCommand but provides a "to" clause
     *                           with incorrect formatting
     * @throws EmptyToException Thrown when the user attempts to create a EventCommand but does not specify the "to"
     *                          clause
     * @throws InvalidFromException Thrown when the user attempts to create a EventCommand but provides a "from" clause
     *                           with incorrect formatting
     * @throws EmptyFromException Thrown when the user attempts to create a EventCommand but does not specify the "from"
     *                          clause
     * @throws NotAnIntegerException Thrown when the user attempts to create mark/unmark/delete commands but does not
     *                              specify an integer as the second argument
     */
    public static Command parse(String userInput) throws InvalidCommandTypeException, EmptyDescriptionException,
            EmptyByException, InvalidByException, InvalidToException, EmptyToException, InvalidFromException,
            EmptyFromException, NotAnIntegerException, ClauselessUpdateException {

        userInput = userInput.trim(); // remove trailing whitespaces

        String commandType = getCommandType(userInput);
        // bye
        if (commandType.equals(TaskMate.CommandTypes.bye.toString())) {
            return new ExitCommand();
        // help
        } else if (commandType.equals(TaskMate.CommandTypes.help.toString())) {
            return new HelpCommand();
        // list
        } else if (commandType.equals(TaskMate.CommandTypes.list.toString())) {
            return new ListCommand();
        // unmark i
        } else if (commandType.equals(TaskMate.CommandTypes.unmark.toString())) {
            checkValidUnmarkCommand(userInput);
            int indexToUnmark = getIndexToUnmark(userInput);
            return new UnmarkCommand(indexToUnmark);
        // mark i
        } else if (commandType.equals(TaskMate.CommandTypes.mark.toString())) {
            checkValidMarkCommand(userInput);
            int indexToMark = getIndexToMark(userInput);
            return new MarkCommand(indexToMark);
        // to-do description
        } else if (commandType.equals(TaskMate.CommandTypes.todo.toString())) {
            checkValidTodoCommand(userInput);
            String todoTaskName = getTodoTaskName(userInput);
            return new TodoCommand(todoTaskName);
        // deadline description /by date
        } else if (commandType.equals(TaskMate.CommandTypes.deadline.toString())) {
            checkValidDeadlineCommand(userInput);
            String deadlineTaskName = getDeadlineTaskName(userInput);
            String deadlineTaskBy = getDeadlineTaskBy(userInput);
            return new DeadlineCommand(deadlineTaskName, deadlineTaskBy);
        // event description /from date /to date
        } else if (commandType.equals(TaskMate.CommandTypes.event.toString())) {
            checkValidEventCommand(userInput);
            String eventTaskName = getEventTaskName(userInput);
            String eventTaskFrom = getEventTaskFrom(userInput);
            String eventTaskTo = getEventTaskTo(userInput);
            return new EventCommand(eventTaskName, eventTaskFrom, eventTaskTo);
        // delete i
        } else if (commandType.equals(TaskMate.CommandTypes.delete.toString())) {
            checkValidDeleteCommand(userInput);
            int indexToDelete = getIndexToDelete(userInput);
            return new DeleteCommand(indexToDelete);
        // find query
        } else if (commandType.equals(TaskMate.CommandTypes.find.toString())) {
            checkValidFindCommand(userInput);
            String query = getFindQuery(userInput);
            return new FindCommand(query);
        // update i <attribute> <change> <attribute> <change> ...
        } else if (commandType.equals(TaskMate.CommandTypes.update.toString())) {
            checkValidUpdateCommand(userInput);
            int indexToUpdate = getIndexToUpdate(userInput);
            HashMap<String, String> changes = getChangesToUpdate(userInput);
            return new UpdateCommand(indexToUpdate, changes);
        // Invalid input
        } else {
            throw new InvalidCommandTypeException();
        }
    }

    /**
     * Returns the type of command input by the user
     * Possible values: "to\-do", "deadline", "event", "bye", "list", "mark", "unmark", "find"
     * @param userInput a String object representing the raw user input
     * @return a String object that represents the user input's command type
     * @throws InvalidCommandTypeException thrown when the command entered by the user is not recognized
     */
    public static String getCommandType(String userInput) throws InvalidCommandTypeException {
        userInput = userInput.trim();

        for (TaskMate.CommandTypes type : TaskMate.CommandTypes.values()) {
            String typeString = type.toString();
            if (userInput.startsWith(typeString)) {
                return typeString;
            }
        }
        throw new InvalidCommandTypeException();
    }

    private static int getIndexToUnmark(String userInput) {
        int indexToUnmark = Integer.parseInt(
                userInput
                        .substring(TaskMate.CommandTypes.unmark.toString().length())
                        .trim());
        indexToUnmark -= 1;
        return indexToUnmark;
    }

    private static int getIndexToMark(String userInput) {
        int indexToMark = Integer.parseInt(
                userInput
                        .substring(TaskMate.CommandTypes.mark.toString().length())
                        .trim());
        indexToMark -= 1;
        return indexToMark;
    }

    private static int getIndexToDelete(String userInput) {
        int indexToDelete = Integer.parseInt(
                userInput
                        .substring(TaskMate.CommandTypes.delete.toString().length())
                        .trim());
        indexToDelete -= 1;
        return indexToDelete;
    }

    private static int getIndexToUpdate(String userInput) {
        int indexToUpdate = Integer.parseInt(
                userInput
                        .substring(TaskMate.CommandTypes.update.toString().length())
                        .trim()
                        .split("\\s+")[0]);
        indexToUpdate -= 1;
        return indexToUpdate;
    }

    private static HashMap<String, String> getChangesToUpdate(String userInput) {
        HashMap<String, String> changesMap = new HashMap<>();

        // Use a regular expression to match "update <number> "
        String pattern = "^update\\s\\d+\\s";
        String changesAsString = userInput.replaceFirst(pattern, "");

        // Extract the user-specified changes and store them in changesMap
        String[] tokens = changesAsString.split("\\s+");
        String[] clauses = new String[] {"/name", "/by", "/from", "/to"};
        String currentClause = null;
        for (String token : tokens) {
            boolean equalsAtLeastOneClause = Arrays.asList(clauses).contains(token);
            if (equalsAtLeastOneClause) {
                currentClause = token.trim();
            } else {
                if (!changesMap.containsKey(currentClause)) {
                    changesMap.put(currentClause, "");
                }
                String newValue = changesMap.get(currentClause) + " " + token;
                changesMap.put(currentClause, newValue.trim());
            }
        }

        return changesMap;
    }

    private static String getFindQuery(String userInput) {
        return userInput
                .substring(TaskMate.CommandTypes.find.toString().length())
                .trim();
    }

    private static String getTodoTaskName(String userInput) {
        String delimiter = TaskMate.CommandTypes.todo.toString();
        return userInput.substring(delimiter.length()).trim();
    }

    private static String getDeadlineTaskName(String userInput) {
        String delimiter = TaskMate.CommandTypes.deadline.toString();
        String removedDeadlineWord = userInput.substring(delimiter.length()).trim();
        String deadlineTaskName = removedDeadlineWord.split(" /")[0];
        return deadlineTaskName.trim();
    }

    private static String getDeadlineTaskBy(String userInput) {
        String delimiter = TaskMate.CommandTypes.deadline.toString();
        String removedDeadlineWord = userInput.substring(delimiter.length()).trim();
        String deadlineTaskBy = removedDeadlineWord.split(" /")[1];
        return deadlineTaskBy.replace("by", "").trim();
    }

    private static String getEventTaskName(String userInput) {
        String delimiter = TaskMate.CommandTypes.event.toString();
        String removedEventWord = userInput.substring(delimiter.length()).trim();
        String eventTaskName = removedEventWord.split("/")[0];
        return eventTaskName.trim();
    }

    private static String getEventTaskFrom(String userInput) {
        String delimiter = TaskMate.CommandTypes.event.toString();
        String removedEventWord = userInput.substring(delimiter.length()).trim();
        String eventTaskFrom = removedEventWord.split("/")[1];
        return eventTaskFrom.replace("from", "").trim();
    }

    private static String getEventTaskTo(String userInput) {
        String delimiter = TaskMate.CommandTypes.event.toString();
        String removedEventWord = userInput.substring(delimiter.length() + 1);
        String eventTaskFrom = removedEventWord.split("/")[2];
        return eventTaskFrom.replace("to", "").trim();
    }

    /**
     * Checks if "to-do" command is valid by checking if there is text coming after the word "to-do"
     */
    private static void checkValidTodoCommand(String userInput) throws InvalidCommandTypeException,
            EmptyDescriptionException {
        if (!userInput.startsWith(TaskMate.CommandTypes.todo.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(TaskMate.CommandTypes.todo.toString().length()).isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Checks if "deadline" command is valid by checking if there is text coming after the word "deadline"
     * Additionally, checks if there is a "/by " substring within userInput, and if the date after "/by " substring
     * can be parsed into a date
     */
    private static void checkValidDeadlineCommand(String userInput) throws InvalidCommandTypeException,
            EmptyDescriptionException, EmptyByException, InvalidByException {
        if (!userInput.startsWith(TaskMate.CommandTypes.deadline.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(TaskMate.CommandTypes.deadline.toString().length()).isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (!userInput.contains("/by ")) {
            throw new EmptyByException();
        } else {
            String delimiter = "/by ";
            String byInput = userInput.substring(userInput.indexOf(delimiter) + delimiter.length());
            boolean isValidDateFormat = checkValidDateFormat(byInput);
            if (!isValidDateFormat) {
                throw new InvalidByException();
            }
        }
    }

    /**
     * Checks if "deadline" command is valid by checking if there is text coming after the word "deadline"
     * Additionally, checks if there are "/from " and "/to " substrings within userInput, and if the dates after
     * "/from " and "/to " substrings can be parsed into a date
     */
    private static void checkValidEventCommand(String userInput) throws InvalidCommandTypeException,
            EmptyDescriptionException,
            EmptyFromException, InvalidFromException, EmptyToException, InvalidToException {

        String fromDelimiter = "/from ";
        String toDelimiter = "/to ";

        if (!userInput.startsWith(TaskMate.CommandTypes.event.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(TaskMate.CommandTypes.event.toString().length()).isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (!userInput.contains(fromDelimiter)) {
            throw new EmptyFromException();
        } else if (!userInput.contains(toDelimiter)) {
            throw new EmptyToException();
        } else {
            // Testing from clause
            String fromInput = userInput.substring(userInput.indexOf(fromDelimiter) + fromDelimiter.length(),
                    userInput.indexOf(toDelimiter)).trim();
            boolean isValidDateFormat = checkValidDateFormat(fromInput);
            if (!isValidDateFormat) {
                throw new InvalidFromException();
            }

            // Testing to clause
            String toInput = userInput.substring(userInput.indexOf(toDelimiter) + toDelimiter.length())
                    .trim();
            isValidDateFormat = checkValidDateFormat(toInput);
            if (!isValidDateFormat) {
                throw new InvalidToException();
            }
        }
    }

    /**
     * Checks if the user input command is a valid "mark" or "unmark" command
     * by checking if the command starts with "mark"/"unmark", followed by a whitespace,
     * followed by an integer
     * Note: Does not check if the integer is within the size of TaskList object
     */
    private static void checkValidMarkCommand(String userInput) throws InvalidCommandTypeException,
            NotAnIntegerException {

        String indexWithinList;
        if (!userInput.startsWith(TaskMate.CommandTypes.mark.toString())) {
            throw new InvalidCommandTypeException();
        }
        indexWithinList = userInput.substring(TaskMate.CommandTypes.mark.toString().length()).trim();
        if (!checkStringIsInteger(indexWithinList)) {
            throw new NotAnIntegerException();
        }
    }

    /**
     * Checks if the user input command is a valid "mark" or "unmark" command
     * by checking if the command starts with "mark"/"unmark", followed by a whitespace,
     * followed by an integer
     * Note: Does not check if the integer is within the size of TaskList object
     */
    private static void checkValidUnmarkCommand(String userInput) throws InvalidCommandTypeException,
            NotAnIntegerException {

        String indexWithinList;
        if (!userInput.startsWith(TaskMate.CommandTypes.unmark.toString())) {
            throw new InvalidCommandTypeException();
        }
        indexWithinList = userInput.substring(TaskMate.CommandTypes.unmark.toString().length()).trim();
        if (!checkStringIsInteger(indexWithinList)) {
            throw new NotAnIntegerException();
        }
    }

    /**
     * Checks if the user input command is a valid "mark" or "unmark" command
     * by checking if the command starts with "mark"/"unmark", followed by a whitespace,
     * followed by an integer
     * Note: Does not check if the integer is within the size of TaskList object
     */
    private static void checkValidDeleteCommand(String userInput) throws InvalidCommandTypeException,
            NotAnIntegerException {

        String indexWithinList;

        if (userInput.startsWith(TaskMate.CommandTypes.delete.toString())) {
            indexWithinList = userInput.substring(TaskMate.CommandTypes.delete.toString().length()).trim();
        } else {
            throw new InvalidCommandTypeException();
        }

        if (!checkStringIsInteger(indexWithinList)) {
            throw new NotAnIntegerException();
        }
    }

    /**
     * Returns true if String s can be parsed into an Integer object, and false otherwise
     */
    private static boolean checkStringIsInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean checkValidDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void checkValidFindCommand(String userInput) throws EmptyDescriptionException,
            InvalidCommandTypeException {
        boolean isStartingWithFind = userInput.startsWith(TaskMate.CommandTypes.find + " ");
        boolean hasEmptyQuery = userInput.substring(TaskMate.CommandTypes.find.toString().length()).trim().isEmpty();

        if (!isStartingWithFind) {
            throw new InvalidCommandTypeException();
        } else if (hasEmptyQuery) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Checks the following cases:
     * 1. The update command starts with the word "update"
     * 2. The update command has a description
     * 3. The update command contains a slash (/)
     * 4. The update command contains an integer after "update "
     * Throws exception if any of the above are not fulfilled
     * @param userInput a String object representing the raw update command from the user
     * @throws InvalidCommandTypeException thrown when case 1 is not satisfied
     * @throws EmptyDescriptionException thrown when case 2 is not satisfied
     * @throws ClauselessUpdateException thrown when case 3 is not satisfied
     * @throws NotAnIntegerException thrown when case 4 is not satisfied
     */
    private static void checkValidUpdateCommand(String userInput) throws InvalidCommandTypeException,
            EmptyDescriptionException, ClauselessUpdateException, NotAnIntegerException {

        String[] tokens = userInput.split("\\s+");

        boolean isStartingWithUpdate = userInput.startsWith(TaskMate.CommandTypes.update + " ");
        if (!isStartingWithUpdate) {
            throw new InvalidCommandTypeException();
        }

        boolean hasEmptyQuery = userInput.substring(TaskMate.CommandTypes.update.toString().length()).trim().isEmpty();
        if (hasEmptyQuery) {
            System.out.println("test"); // todo remove
            throw new EmptyDescriptionException();
        }

        String[] clauses = new String[] {" /name ", " /by ", " /from ", " /to "};
        boolean containsAtLeastOneClause = Arrays.stream(clauses).anyMatch(userInput::contains);
        if (!containsAtLeastOneClause) {
            throw new ClauselessUpdateException();
        }

        boolean updateIndexIsInteger = checkStringIsInteger(tokens[1]);
        if (!updateIndexIsInteger) {
            throw new NotAnIntegerException();
        }
    }
}
