package taskmate.tools;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     * Reads the user input and returns a Command object that represents the input.
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
    public static Command parse(String userInput) throws
            InvalidCommandTypeException, EmptyDescriptionException,
            EmptyByException, InvalidByException, InvalidToException,
            EmptyToException, InvalidFromException, EmptyFromException,
            NotAnIntegerException {

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
            int indexToUnmark = Integer.parseInt(userInput.substring(TaskMate.CommandTypes.unmark.toString().length())
                    .trim());
            indexToUnmark -= 1;
            return new UnmarkCommand(indexToUnmark);
        // mark i
        } else if (commandType.equals(TaskMate.CommandTypes.mark.toString())) {
            checkValidMarkCommand(userInput);
            int indexToMark = Integer.parseInt(userInput.substring(TaskMate.CommandTypes.mark.toString().length())
                    .trim());
            indexToMark -= 1;
            return new MarkCommand(indexToMark);
        // to-do description
        } else if (commandType.equals(TaskMate.CommandTypes.todo.toString())) {
            checkValidTodoCommand(userInput);
            return new TodoCommand(userInput.substring(TaskMate.CommandTypes.todo.toString()
                    .length() + 1)); // +1 because we do not want the task name to start from the space character
        // deadline description /by date
        } else if (commandType.equals(TaskMate.CommandTypes.deadline.toString())) {
            checkValidDeadlineCommand(userInput);
            userInput = userInput.substring(TaskMate.CommandTypes.deadline.toString()
                    .length() + 1); // +1 because we do not want the task name to start from the space character
            String[] splitUserInput = userInput.split(" /");
            return new DeadlineCommand(
                    splitUserInput[0],
                    splitUserInput[1].replace("by ", "")
            );
        // event description /from date /to date
        } else if (commandType.equals(TaskMate.CommandTypes.event.toString())) {
            checkValidEventCommand(userInput);
            userInput = userInput.substring(TaskMate.CommandTypes.event.toString()
                    .length() + 1); // +1 because we do not want the task name to start from the space character
            String[] splitUserInput = userInput.split(" /");
            return new EventCommand(
                    splitUserInput[0],
                    splitUserInput[1].replace("from ", ""),
                    splitUserInput[2].replace("to ", "")
            );
        // delete i
        } else if (commandType.equals(TaskMate.CommandTypes.delete.toString())) {
            checkValidDeleteCommand(userInput);
            int indexToDelete = Integer.parseInt(userInput.substring(TaskMate.CommandTypes.delete.toString().length())
                    .trim());
            indexToDelete -= 1; // subtract 1 as the arraylist is zero-indexed
            return new DeleteCommand(indexToDelete);
        } else if (commandType.equals(TaskMate.CommandTypes.find.toString())) {
            checkValidFindCommand(userInput);
            String query = userInput.substring(TaskMate.CommandTypes.find.toString().length()).trim();
            return new FindCommand(query);
        // Invalid input
        } else {
            throw new InvalidCommandTypeException();
        }
    }



    static String getCommandType(String userInput) throws InvalidCommandTypeException {
        // Returns the type of command input by the user
        // Possible values: "to\-do", "deadline", "event", "bye", "list", "mark", "unmark", "find"

        userInput = userInput.trim();

        for (TaskMate.CommandTypes type : TaskMate.CommandTypes.values()) {
            String typeString = type.toString();
            if (userInput.startsWith(typeString)) {
                return typeString;
            }
        }
        throw new InvalidCommandTypeException();
    }

    static void checkValidTodoCommand(String userInput) throws InvalidCommandTypeException, EmptyDescriptionException {
        // Checks if "to-do" command is valid by checking if there is text coming after the word "to-do"
        if (!userInput.startsWith(TaskMate.CommandTypes.todo.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(TaskMate.CommandTypes.todo.toString().length()).isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    static void checkValidDeadlineCommand(String userInput) throws InvalidCommandTypeException,
            EmptyDescriptionException, EmptyByException, InvalidByException {
        // Checks if "deadline" command is valid by checking if there is text coming after the word "deadline"
        // Additionally, checks if there is a "/by " substring within userInput, and if the date after "/by " substring
        // can be parsed into a date
        if (!userInput.startsWith(TaskMate.CommandTypes.deadline.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(TaskMate.CommandTypes.deadline.toString().length()).isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (!userInput.contains("/by ")) {
            throw new EmptyByException();
        } else {
            try {
                String delimiter = "/by ";
                String byInput = userInput.substring(userInput.indexOf(delimiter) + delimiter.length());
                LocalDate.parse(byInput);
            } catch (DateTimeParseException e) {
                throw new InvalidByException();
            }
        }
    }

    static void checkValidEventCommand(String userInput) throws InvalidCommandTypeException, EmptyDescriptionException,
            EmptyFromException, InvalidFromException, EmptyToException, InvalidToException {
        // Checks if "deadline" command is valid by checking if there is text coming after the word "deadline"
        // Additionally, checks if there are "/from " and "/to " substrings within userInput, and if the dates after
        // "/from " and "/to " substrings can be parsed into a date

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
            try {
                System.out.println(userInput);
                String fromInput = userInput.substring(userInput.indexOf(fromDelimiter) + fromDelimiter.length(),
                        userInput.indexOf(toDelimiter)).trim();
                LocalDate.parse(fromInput);
            } catch (DateTimeParseException e) {
                throw new InvalidFromException();
            }

            // Testing to clause
            try {
                String toInput = userInput.substring(userInput.indexOf(toDelimiter) + toDelimiter.length())
                        .trim();
                LocalDate.parse(toInput);
            } catch (DateTimeParseException e) {
                throw new InvalidToException();
            }
        }
    }

    static void checkValidMarkCommand(String userInput) throws InvalidCommandTypeException, NotAnIntegerException {
        // Checks if the user input command is a valid "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace,
        // followed by an integer
        // Note: Does not check if the integer is within the size of TaskList object
        String indexWithinList;
        if (!userInput.startsWith(TaskMate.CommandTypes.mark.toString())) {
            throw new InvalidCommandTypeException();
        }
        indexWithinList = userInput.substring(TaskMate.CommandTypes.mark.toString().length()).trim();
        if (!checkStringIsInteger(indexWithinList)) {
            throw new NotAnIntegerException();
        }
    }

    static void checkValidUnmarkCommand(String userInput) throws InvalidCommandTypeException, NotAnIntegerException {
        // Checks if the user input command is a valid "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace,
        // followed by an integer
        // Note: Does not check if the integer is within the size of TaskList object
        String indexWithinList;
        if (!userInput.startsWith(TaskMate.CommandTypes.unmark.toString())) {
            throw new InvalidCommandTypeException();
        }
        indexWithinList = userInput.substring(TaskMate.CommandTypes.unmark.toString().length()).trim();
        if (!checkStringIsInteger(indexWithinList)) {
            throw new NotAnIntegerException();
        }
    }

    static void checkValidDeleteCommand(String userInput) throws InvalidCommandTypeException, NotAnIntegerException {
        // Checks if the user input command is a valid "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace,
        // followed by an integer
        // Note: Does not check if the integer is within the size of TaskList object
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

    static boolean checkStringIsInteger(String s) {
        // Returns true if String s can be parsed into an Integer object, and false otherwise
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static void checkValidFindCommand(String userInput) throws EmptyDescriptionException, InvalidCommandTypeException {
        boolean isStartingWithFind = userInput.startsWith(TaskMate.CommandTypes.find + " ");
        boolean hasEmptyQuery = userInput.substring(TaskMate.CommandTypes.find.toString().length()).trim().isEmpty();

        if (!isStartingWithFind) {
            throw new InvalidCommandTypeException();
        } else if (hasEmptyQuery) {
            throw new EmptyDescriptionException();
        }
    }

}
