package jeeves.parser;

import java.util.ArrayList;

import jeeves.exception.MissingByException;
import jeeves.exception.MissingDescriptionException;
import jeeves.exception.MissingFromException;
import jeeves.exception.MissingIdException;
import jeeves.exception.MissingToException;
import jeeves.exception.NotIntegerIdException;
import jeeves.exception.OutOfBoundIdException;
import jeeves.task.Task;

/**
 * Parser processes user input and returns an arraylist of string tokens for other classes to use.
 */
public class Parser {

    private static final int FIND_COMMAND_TODO_OFFSET = 5;
    private static final int FIND_COMMAND_DEADLINE_OFFSET = 9;
    private static final int FIND_COMMAND_EVENT_OFFSET = 6;
    private static final int FIND_COMMAND_MARK_OFFSET = 5;
    private static final int FIND_COMMAND_UNMARK_OFFSET = 7;
    private static final int FIND_COMMAND_DELETE_OFFSET = 7;
    private static final int FIND_COMMAND_FIND_OFFSET = 5;
    private static final int FIND_FIELD_TO_OFFSET = 4;
    private static final int FIND_FIELD_FROM_OFFSET = 6;
    private static final int FIND_FIELD_BY_OFFSET = 4;

    /**
     * Constructor for a Parser object.
     * Currently, requires no arguments to initialize.
     */
    public Parser() {

    }

    /**
     * Takes the user's input line and returns an array list of relevant string tokens for usage.
     * Used by the live implementation of Jeeves.
     *
     * @param input A line of user input.
     * @return String ArrayList containing relevant useful string tokens
     */
    public ArrayList<String> parseUserInput(String input) throws MissingByException, MissingDescriptionException,
            MissingFromException, MissingToException, OutOfBoundIdException, NotIntegerIdException,
            MissingIdException {
        ArrayList<String> args = new ArrayList<>();
        if (input.equals("list")) {
            // returns args <command>
            args.add(input);
        } else if (input.startsWith("mark ")) {
            args.add("mark");
            // Gets the task ID that the user wish to mark
            String idString = input.substring(FIND_COMMAND_MARK_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command,id>
            args.add(idString);
        } else if (input.startsWith("unmark ")) {
            args.add("unmark");
            // Gets the task ID that the user wish to unmark
            String idString = input.substring(FIND_COMMAND_UNMARK_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command,id>
            args.add(idString);
        } else if (input.startsWith("delete ")) {
            args.add("delete");
            // Gets the task ID that the user wish to delete
            String idString = input.substring(FIND_COMMAND_DELETE_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command, id>
            args.add(idString);
        } else if (input.startsWith("todo ")) {
            args.add("todo");
            // Checks if the user provided a description
            // If so, returns args <command, desc>
            // Else throws the custom MissingDescriptionException error
            String desc = input.substring(FIND_COMMAND_TODO_OFFSET);
            checkIfDescMissing(desc);
            args.add(desc);
        } else if (input.startsWith("deadline ")) {
            args.add("deadline");
            // Checks if the user has entered the command properly
            // and extracts the relevant information to parse
            // then returns args <command, desc, dyDate>
            int byDateIndex = getByDateIndex(input);
            String desc = input.substring(FIND_COMMAND_DEADLINE_OFFSET, byDateIndex - 1);
            String byDate = input.substring(byDateIndex + FIND_FIELD_BY_OFFSET);
            args.add(desc);
            args.add(byDate);
        } else if (input.startsWith("event ")) {
            args.add("event");
            // Checks if the user has entered the command properly
            // and extracts the relevant information to parse
            // then returns args <command, desc, fromDate, toDate>
            int fromDateIndex = getFromDateIndex(input);
            int toDateIndex = getToDateIndex(input, fromDateIndex);
            String desc = input.substring(FIND_COMMAND_EVENT_OFFSET, fromDateIndex - 1);
            String fromDate = input.substring(fromDateIndex + FIND_FIELD_FROM_OFFSET, toDateIndex - 1);
            String toDate = input.substring(toDateIndex + FIND_FIELD_TO_OFFSET);
            args.add(desc);
            args.add(fromDate);
            args.add(toDate);
        } else if (input.startsWith("find ")) {
            // Gets the search term the user wishes to find
            // and returns args <command, findTerm>
            args.add("find");
            String findTerm = input.substring(FIND_COMMAND_FIND_OFFSET);
            args.add(findTerm);
        } else if (input.equals("bye")) {
            args.add(input);
        } else {
            args.add("invalid_command");
        }
        return args;
    }

    /**
     * Checks if a string is only made up of integer numbers.
     * Uses regex to determine if a string contains only integers
     *
     * @param input The string to be checked
     * @return Whether the string is fully comprised of integers
     */
    static boolean isNotNumber(String input) {
        return !input.matches("[0-9]+");
    }

    /**
     * Checks if the string provided can be parsed into a valid id Integer.
     * Throws a custom exception with an appropriate error message upon encountering an error.
     * Currently, checks if the idString is: Present, is a Number, is within the usable range.
     *
     * @param idString The string to be checked.
     */
    static void checkIfValidId(String idString) throws MissingIdException, NotIntegerIdException,
            OutOfBoundIdException {
        if (idString.isEmpty()) {
            // id field is empty
            throw new MissingIdException();
        } else if (isNotNumber(idString)) {
            // id field is not an integer
            throw new NotIntegerIdException();
        } else if (Integer.parseInt(idString) > Task.getTaskCount()) {
            // id does not exist
            throw new OutOfBoundIdException();
        }
    }

    /**
     * Checks if the string provided is not empty
     * Throws a custom exception with an appropriate error message if it is.
     * Else does nothing.
     *
     * @param description The string to be checked.
     */
    static void checkIfDescMissing(String description) throws MissingDescriptionException {
        if (description.isEmpty()) {
            throw new MissingDescriptionException("The description of a todo cannot be empty\n");
        }
    }

    static int getByDateIndex(String command) throws MissingByException, MissingDescriptionException {
        int byDateIndex = command.indexOf("/by ");
        if (byDateIndex == -1 || command.length() == byDateIndex + FIND_FIELD_BY_OFFSET) {
            // If the "/by " block is missing, throws the MissingByException
            throw new MissingByException();
        }
        if ((byDateIndex - 1) <= FIND_COMMAND_DEADLINE_OFFSET) {
            throw new MissingDescriptionException("The description of a deadline cannot be empty\n");
        }
        return byDateIndex;
    }

    static int getFromDateIndex(String input) throws MissingFromException {
        int fromDateIndex = input.indexOf("/from ");
        if (fromDateIndex == -1) {
            // If the "/from " block is missing, throws the MissingFromException
            throw new MissingFromException();
        }
        return fromDateIndex;
    }

    static int getToDateIndex(String input, int fromDateIndex) throws MissingToException,
            MissingDescriptionException, MissingFromException {
        int toDateIndex = input.indexOf("/to ");
        if (toDateIndex == -1) {
            // If the "/to " block is missing, throws the MissingToException
            throw new MissingToException();
        }
        if (input.length() == (toDateIndex + FIND_FIELD_TO_OFFSET)) {
            // If the /to block is present but no data has been given to the field, throw
            // the MissingToException
            throw new MissingToException();
        }
        if ((fromDateIndex - 1) <= FIND_COMMAND_DEADLINE_OFFSET) {
            //If the description is missing, throw the MissingDescription Exception
            throw new MissingDescriptionException("The description of a event cannot be empty\n");
        }
        if ((toDateIndex - 1) <= (fromDateIndex + FIND_FIELD_FROM_OFFSET)) {
            // If the /from block is present but no data has been given to the field, throw
            // the MissingFromException
            throw new MissingFromException();
        }
        return toDateIndex;
    }
}
