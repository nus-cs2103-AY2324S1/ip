package arona.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import arona.exception.IllegalArgumentAronaException;

/**
 * The `Parser` class is responsible for parsing user input and extracting relevant information.
 */
public class Parser {

    /**
     * Parses the user input into an array of tokens, splitting by spaces.
     *
     * @param userInput The user's input as a single string.
     * @return An array of tokens.
     */
    public static String[] parseUserInput(String userInput) {
        return userInput.split("\\s+");
    }

    /**
     * Extracts the command keyword from the tokens.
     *
     * @param tokens The array of tokens.
     * @return The command keyword as a lowercase string.
     */
    public static String getCommand(String[] tokens) {
        if (tokens.length > 0) {
            return tokens[0].toLowerCase();
        }
        return "";
    }

    /**
     * Gets the description of a to-do task from the tokens.
     *
     * @param tokens The array of tokens.
     * @return The description of the to-do task.
     * @throws IllegalArgumentAronaException If the description is missing.
     */
    public static String getToDoDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the task!");
        }
        return String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    /**
     * Gets the description and deadline date from the tokens for a deadline task.
     *
     * @param tokens The array of tokens.
     * @return An array containing the description and deadline date.
     * @throws IllegalArgumentAronaException If the description or '/by' is missing.
     */
    public static String[] getDeadlineDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the task!");
        }

        boolean byFound = false;
        for (String token : tokens) {
            if (token.equals("/by")) {
                byFound = true;
                break;
            }
        }

        if (!byFound) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use '/by' to set it.");
        }

        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                index = i;
                tokens[i] = tokens[i].substring(1) + ":";
                break;
            }
        }
        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, index));
        String by = String.join(" ", Arrays.copyOfRange(tokens, index + 1, tokens.length));

        String[] descriptions = {description, by};
        return descriptions;
    }

    /**
     * Gets the description, start date, and end date from the tokens for an event task.
     *
     * @param tokens The array of tokens.
     * @return An array containing the description, start date, and end date.
     * @throws IllegalArgumentAronaException If the description, '/from', or '/to' is missing.
     */
    public static String[] getEventDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the event!");
        }

        boolean foundFrom = false;
        boolean foundTo = false;

        for (String token : tokens) {
            if (token.equals("/from")) {
                foundFrom = true;
            } else if (foundFrom && token.equals("/to")) {
                foundTo = true;
                break;
            }
        }

        if (!(foundFrom && foundTo)) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use '/from' and '/to' to set it.");
        }

        int indexStart = -1;
        int indexEnd = -1;
        boolean first = true;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                if (first) {

                    indexStart = i;
                    first = false;
                    tokens[i] = tokens[i].substring(1) + ":";
                } else {
                    indexEnd = i;
                    tokens[i] = tokens[i].substring(1) + ":";
                    break;
                }
            }
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, indexStart));
        String from = String.join(" ", Arrays.copyOfRange(tokens, indexStart, indexEnd));
        String to = String.join(" ", Arrays.copyOfRange(tokens, indexEnd, tokens.length));

        if (from.substring(5).trim().isEmpty() || to.substring(3).trim().isEmpty()) {
            throw new IllegalArgumentAronaException("Oh no! The start and/or end time cannot be empty.");
        }

        String[] descriptions = {description, from, to};
        return descriptions;
    }


    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string in 'YYYY-MM-DD' format.
     * @return The parsed LocalDate.
     * @throws IllegalArgumentAronaException If the date format is invalid.
     */
    public static LocalDate parseDate(String dateStr) throws IllegalArgumentAronaException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use a 'YYYY-MM-DD' format to set it.");
        }
    }

    /**
     * Gets the task index from the tokens, used for marking or deleting tasks.
     *
     * @param tokens The array of tokens.
     * @return The task index.
     */
    public static int getTaskIndex(String[] tokens) {
        if (tokens.length > 1) {
            try {
                return Integer.parseInt(tokens[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    /**
     * Extracts and returns a keyword from an array of tokens.
     *
     * @param tokens An array of tokens containing user input.
     * @return The keyword to be used for searching.
     * @throws IllegalArgumentAronaException If the input format is incorrect:
     *                                      - If no keyword is specified, an exception is thrown.
     *                                      - If more than one keyword is provided, an exception is thrown.
     */
    public static String getKeyWord(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Please specify a keyword I can search for you");
        } else if (tokens.length > 2) {
            throw new IllegalArgumentAronaException("Sorry... I can only handle one keyword");
        } else {
            return tokens[1];
        }
    }
}

