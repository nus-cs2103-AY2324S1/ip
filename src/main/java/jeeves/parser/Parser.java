package jeeves.parser;

import jeeves.exception.MissingByException;
import jeeves.exception.MissingDescriptionException;
import jeeves.exception.MissingFromException;
import jeeves.exception.MissingIdException;
import jeeves.exception.MissingToException;
import jeeves.exception.NotIntegerIdException;
import jeeves.exception.OutOfBoundIdException;
import jeeves.task.Task;

import java.util.ArrayList;

public class Parser {

    private static final int FINDCOMMAND_TODO_OFFSET = 5;
    private static final int FINDCOMMAND_DEADLINE_OFFSET = 9;
    private static final int FINDCOMMAND_EVENT_OFFSET = 6;
    private static final int FINDCOMMAND_MARK_OFFSET = 5;
    private static final int FINDCOMMAND_UNMARK_OFFSET = 7;
    private static final int FINDCOMMAND_DELETE_OFFSET = 7;
    private static final int FINDFIELD_TO_OFFSET = 4;
    private static final int FINDFIELD_FROM_OFFSET = 6;
    private static final int FINDFIELD_BY_OFFSET = 4;
    
    public Parser() {
        
    }
    
    public ArrayList<String> parseUserInput(String input) {
        ArrayList<String> args = new ArrayList<>();
        if (input.equals("list")) {
            // returns args <command>
            args.add(input);
        } else if (input.startsWith("mark ")) {
            args.add("mark");
            // Gets the task ID that the user wish to mark
            String idString = input.substring(FINDCOMMAND_MARK_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command,id>
            args.add(idString);
        } else if (input.startsWith("unmark ")) {
            args.add("unmark");
            // Gets the task ID that the user wish to unmark
            String idString = input.substring(FINDCOMMAND_UNMARK_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command,id>
            args.add(idString);
        } else if (input.startsWith("delete ")) {
            args.add("delete");
            // Gets the task ID that the user wish to delete
            String idString = input.substring(FINDCOMMAND_DELETE_OFFSET);
            // Checks if the task ID is invalid, program will throw an error if the id is invalid
            checkIfValidId(idString);
            // Adds the valid id to the args array list and returns args <command, id>
            args.add(idString);
        } else if (input.startsWith("todo ")) {
            args.add("todo");
            // Checks if the user provided a description
            // If so, returns args <command, desc>
            // Else throws the custom MissingDescriptionException error
            String desc = input.substring(FINDCOMMAND_TODO_OFFSET);
            checkIfDescMissing(desc);
            args.add(desc);
        } else if (input.startsWith("deadline ")) {
            args.add("deadline");
            // Checks if the user has entered the command properly
            // and extracts the relevant information to parse
            // then returns args <command, desc, dyDate>
            try {
                int byDateIndex = getByDateIndex(input);
                String desc = input.substring(FINDCOMMAND_DEADLINE_OFFSET, byDateIndex - 1);
                String byDate = input.substring(byDateIndex + FINDFIELD_BY_OFFSET);
                args.add(desc);
                args.add(byDate);
            } catch (MissingDescriptionException | MissingByException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("event ")) {
            args.add("event");
            // Checks if the user has entered the command properly
            // and extracts the relevant information to parse
            // then returns args <command, desc, fromDate, toDate>
            try {
                int fromDateIndex = getFromDateIndex(input);
                int toDateIndex = getToDateIndex(input, fromDateIndex);
                String desc = input.substring(FINDCOMMAND_EVENT_OFFSET, fromDateIndex - 1);
                String fromDate = input.substring(fromDateIndex + FINDFIELD_FROM_OFFSET, toDateIndex - 1);
                String toDate = input.substring(toDateIndex + FINDFIELD_TO_OFFSET);
                args.add(desc);
                args.add(fromDate);
                args.add(toDate);
            } catch (MissingDescriptionException | MissingFromException | MissingToException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.equals("bye")) {
            args.add(input);
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
    private static boolean isNotNumber(String input) {
        return !input.matches("[0-9]+");
    }
    
    private static void checkIfValidId(String idString) {
        try {
            if (idString.isEmpty()) {
                // id field is empty
                throw new MissingIdException("I cannot do that as you have not provided me with a Task ID\n");
            } else if (isNotNumber(idString)) {
                // id field is not an integer
                throw new NotIntegerIdException("I cannot do that as that is not a valid Task ID "
                        + "(ID provided is not an integer)\n");
            } else if (Integer.parseInt(idString) > Task.getTaskCount()) {
                // id does not exist
                throw new OutOfBoundIdException("I cannot do that as that is not a valid Task ID "
                        + "(ID provided does not exist)\n");
            } 
        } catch (MissingIdException | NotIntegerIdException | OutOfBoundIdException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void checkIfDescMissing(String desc) {
        try {
            if (desc.isEmpty()) {
                throw new MissingDescriptionException("The description of a todo cannot be empty\n");
            }
        } catch (MissingDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getByDateIndex(String cmd) throws MissingByException, MissingDescriptionException {
        int byDateIndex = cmd.indexOf("/by ");
        if (byDateIndex == -1 || cmd.length() == byDateIndex + FINDFIELD_BY_OFFSET) {
            // If the "/by " block is missing, throws the MissingByException
            throw new MissingByException("I cannot do that as the deadline has not been provided.\n"
                    + "Please add ' /by <YYYY-MM-DD>' after the task description\n");
        }
        if ((byDateIndex - 1) <= FINDCOMMAND_DEADLINE_OFFSET) {
            throw new MissingDescriptionException("The description of a deadline cannot be empty\n");
        }
        return byDateIndex;
    }

    private static int getFromDateIndex(String input) throws MissingFromException, MissingToException, MissingDescriptionException {
        int fromDateIndex = input.indexOf("/from ");
        if (fromDateIndex == -1) {
            // If the "/from " block is missing, throws the MissingFromException
            throw new MissingFromException("I cannot do that as the start time has not been provided.\n"
                    + "Please add ' /from <Time/Date>' after the task description\n");
        }
        return fromDateIndex;
    }

    private static int getToDateIndex(String input, int fromDateIndex) throws MissingToException,
            MissingDescriptionException, MissingFromException {
        int toDateIndex = input.indexOf("/to ");
        if (toDateIndex == -1) {
            // If the "/to " block is missing, throws the MissingByException
            throw new MissingToException("I cannot do that as the end time has not been provided.\n"
                    + "Please add ' /to <Time/Date>' after the task end date (after /from block)\n");
        }
        if (input.length() == (toDateIndex + FINDFIELD_TO_OFFSET)) {
            // If the /to block is present but no data has been given to the field, throw 
            // the MissingToException
            throw new MissingToException("I cannot do that as the end time has not been provided.\n"
                    + "Please add ' /to <Time/Date>' after the task end date (after /from block)\n");
        }
        if ((fromDateIndex - 1) <= FINDCOMMAND_DEADLINE_OFFSET) {
            //If the description is missing, throw the MissingDescription Exception
            throw new MissingDescriptionException("The description of a event cannot be empty\n");
        }
        if ((toDateIndex - 1) <= (fromDateIndex + FINDFIELD_FROM_OFFSET)) {
            // If the /from block is present but no data has been given to the field, throw 
            // the MissingFromException
            throw new MissingFromException("I cannot do that as the start time has not been provided.\n"
                    + "Please add ' /from <Time/Date>' after the task description\n");
        }
        return toDateIndex;
    }
}
