package cheems.functionalities;

import cheems.exceptions.EmptyArgumentException;
import cheems.exceptions.InvalidKeywordException;


/**
 * Parser parses the input from the user and execute corresponding actions.
 * Exceptions are thrown should the input be invalid.
 */
public class Parser {
    private TaskList tasklist;

    public Parser(TaskList listManager) {
        tasklist = listManager;
    }

    /**
     * Parses the given input and tells Tasklist the action to take.
     * Exceptions are thrown should the input be invalid.
     * @param input User input.
     * @throws InvalidKeywordException if the keyword is not recognised.
     * @throws EmptyArgumentException if no argument is provided for commands requiring arguments.
     * @throws NumberFormatException if non-digit argument is provided for commands requiring digits as arguments.
     */
    public String parseAndExecute(String input)
            throws InvalidKeywordException, EmptyArgumentException, NumberFormatException {

        if (input.isEmpty()) {
            String resp = "Please give me instructions, if not, I'll serve you some fries.";
            return resp;
        }

        assert !input.isEmpty();
        String[] words = input.split(" ", 2);

        String command = words[0];
        if (!inKeywords(command)) {
            throw new InvalidKeywordException();
        }

        Keyword currentKey = Keyword.valueOf(words[0].toUpperCase());

        if (currentKey == Keyword.LIST) {
            return tasklist.getTaskList();
        }

        if (words.length == 1) {
            throw new EmptyArgumentException(currentKey.toString());
        }

        String args = words[1];
        switch (currentKey) {
        case FIND:
            return tasklist.findTasks(args);
        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        case DELETE:
            if (!args.chars().allMatch(Character::isDigit)) {
                String errMsg = "Sorry you must provide the task number to mark/unmark it!";
                throw new NumberFormatException(errMsg);
            }

            int index = Integer.parseInt(args);
            switch (currentKey) {
            case MARK:
                return tasklist.markAsDone(index - 1);
            case UNMARK:
                return tasklist.markAsNotDone(index - 1);
            case DELETE:
                return tasklist.delete(index - 1);
            }
        case TODO:
            return tasklist.addTaskToDatabase("TODO", args);
        case EVENT:
            words = args.split(" /from ");
            String eventDescription = words[0];
            String[] words1 = words[1].split(" /to ");
            String from = words1[0];
            String to = words1[1];
            return tasklist.addTaskToDatabase("EVENT", eventDescription, from, to);
        case DEADLINE:
            words = args.split(" /by ");
            String ddlDescription = words[0];
            String by = words[1];
            return tasklist.addTaskToDatabase("DEADLINE", ddlDescription, by);
        default:
            assert false : currentKey;
        }
        throw new RuntimeException("Should not reach here in parser.");
    }

    private boolean inKeywords(String word) {
        for (Keyword k: Keyword.values()) {
            if (k.name().toLowerCase().equals(word)) {
                return true;
            }
        }
        return false;
    }
}