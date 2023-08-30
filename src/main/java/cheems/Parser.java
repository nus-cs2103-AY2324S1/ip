package cheems;

import cheems.exceptions.EmptyArgumentException;
import cheems.exceptions.InvalidKeywordException;

/**
 * Parser parses the input from the user and execute corresponding actions.
 * Exceptions are thrown should the input be invalid.
 */
public class Parser {

    /**
     * Parses the given input and tells Tasklist the action to take.
     * Exceptions are thrown should the input be invalid.
     * @param input User input.
     * @throws InvalidKeywordException if the keyword is not recognised.
     * @throws EmptyArgumentException if no argument is provided for commands requiring arguments.
     * @throws NumberFormatException if non-digit argument is provided for commands requiring digits as arguments.
     */
    public static void parseAndExecute(String input)
            throws InvalidKeywordException, EmptyArgumentException, NumberFormatException {
        if (!input.isEmpty()) {
            String[] words = input.split(" ", 2);

            // if keyword not recognised
            if (!inKeywords(words[0])) {
                throw new InvalidKeywordException();
            }

            // extract command as the first word
            Keyword currentKey = Keyword.valueOf(words[0].toUpperCase());
            if (currentKey == Keyword.LIST) {
                Tasklist.displayData();
            } else {
                // if there is no argument provided for commands supposed to have arguments
                if (words.length == 1) {
                    throw new EmptyArgumentException(currentKey.toString());
                }

                String args = words[1];
                // if there are indeed arguments provided
                switch (currentKey) {
                    case FIND:
                        Tasklist.find(args);
                        break;
                    case MARK:
                        // Fallthrough
                    case UNMARK:
                        // Fallthrough
                    case DELETE:
                        if (args.chars().allMatch(Character::isDigit)) {
                            int index = Integer.parseInt(args);
                            if (currentKey == Keyword.MARK) {
                                Tasklist.markAsDone(index - 1);
                            } else if (currentKey == Keyword.UNMARK) {
                                Tasklist.markAsNotDone(index - 1);
                            } else {
                                Tasklist.delete(index - 1);
                            }
                            break;
                        } else {
                            String errMsg = "Sorry you must provide the task number to mark/unmark it!";
                            throw new NumberFormatException(errMsg);
                        }

                    case TODO:
                        Tasklist.addTaskToDatabase("TODO", args);
                        break;
                    case EVENT:
                        words = args.split(" /from ");
                        String eventDescription = words[0];
                        String[] words1 = words[1].split(" /to ");
                        String from = words1[0];
                        String to = words1[1];
                        Tasklist.addTaskToDatabase("EVENT", eventDescription, from, to);
                        break;
                    case DEADLINE:
                        words = args.split(" /by ");
                        String ddlDescription = words[0];
                        String by = words[1];
                        Tasklist.addTaskToDatabase("DEADLINE", ddlDescription, by);
                        break;
                }
            }
        } else {
            String resp = "Please give me instructions, if not, I'll serve you some fries.";
            UI.printWithFormat(resp);
        }
    }

    private static boolean inKeywords(String word) {
        for (Keyword k: Keyword.values()) {
            if (k.name().toLowerCase().equals(word)) {
                return true;
            }
        }
        return false;
    }

}




