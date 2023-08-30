package cheems;

import cheems.exceptions.EmptyArgumentException;
import cheems.exceptions.InvalidKeywordException;

public class Parser {
    public static void parseAndExecute(String input)
            throws InvalidKeywordException, EmptyArgumentException, NumberFormatException {
        if (!input.isEmpty()) {
            String[] words = input.split(" ", 2);

            // if keyword not recognised
            if (!inKeywords(words[0])) {
                throw new InvalidKeywordException();
            }

            Keyword currentKey = Keyword.valueOf(words[0].toUpperCase());
            if (currentKey == Keyword.LIST) {
                Tasklist.displayData();
            } else {

                // if empty arguments
                if (words.length == 1) {
                    throw new EmptyArgumentException(currentKey.toString());
                }

                switch (currentKey) {
                    case MARK:
                        // Fallthrough
                    case UNMARK:
                        // Fallthrough
                    case DELETE:
                        if (words[1].chars().allMatch(Character::isDigit)) {
                            int index = Integer.parseInt(words[1]);
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

                        // input of cheems.tasks are assumed to be in correct format
                    case TODO:
                        Tasklist.addSaveTask("TODO", words[1]);
                        break;
                    case EVENT:
                        words = words[1].split(" /from ");
                        String eventDescription = words[0];
                        words = words[1].split(" /to ");
                        String from = words[0];
                        String to = words[1];
                        Tasklist.addSaveTask("EVENT", eventDescription, from, to);
                        break;
                    case DEADLINE:
                        words = words[1].split(" /by ");
                        String ddlDescription = words[0];
                        String by = words[1];
                        Tasklist.addSaveTask("DEADLINE", ddlDescription, by);
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




