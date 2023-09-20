package OOP;

import Commands.*;
import Duke.DukeException;

/**
 * Parser class that parses the string command passed in by the user.
 */
public class Parser {
    /**
     * Parses the users command text and returns an appropriate command.
     *
     * @param userCommandText The user's input which is the desired command.
     * @return A Command object which has a certain execution behaviour depending on the command.
     */
    public static Command parseCommand(String userCommandText) {
        if (userCommandText.equals("bye")) {
            assert userCommandText.equals("bye");
            return new ExitCommand();
        } else if (userCommandText.equals("list")) {
            assert userCommandText.equals("list");
            return new ListTasksCommand();
        }

        String[] inputWords = userCommandText.split(" ");
        int id;
        switch (inputWords[0]) {
        case "todo":
            String todoName = extractSecondWordOnwards(userCommandText);
            return new AddToDoCommand(todoName);
        case "deadline":
            String[] twoParts = parseDeadlineCommand(userCommandText);
            return new AddDeadlineCommand(twoParts[0], twoParts[1]);
        case "event":
            String[] threeParts = parseEventCommand(userCommandText);
            return new AddEventCommand(threeParts[0], threeParts[1], threeParts[2]);
        case "mark":
            id = parseIdCommand(inputWords);
            return new MarkTaskCommand(id);
        case "unmark":
            id = parseIdCommand(inputWords);
            return new UnmarkTaskCommand(id);
        case "delete":
            id = parseIdCommand(inputWords);
            return new DeleteTaskCommand(id);
        case "find":
            String searchText = extractSecondWordOnwards(userCommandText);
            return new FindTasksCommand(searchText);
        case "help":
            return new HelpCommand();

        default:
            assert !(userCommandText.equals("list") || userCommandText.equals("bye"));
            return new InvalidCommand();
        }
    }

    /**
     * Returns a string that contains everything after the first whitespace character.
     *
     * @param str The original string.
     * @return secondWordOnwards The string containing only the string from the second word onwards of the original string.
     */

    public static String extractSecondWordOnwards(String str) {
        String[] wordArray = str.split(" ");
        String secondWordOnwards = wordArray.length >= 2 ? wordArray[1] : "";
        for (int i = 2; i < wordArray.length; i++) {
            secondWordOnwards += " " + wordArray[i];
        }
        return secondWordOnwards;
    }

    /**
     * Parses a deadline command string to return a String array, first element being the deadlineName,
     * second element being deadlineString.
     *
     * @param userCommandText The unprocessed command text from the user to add a deadline.
     * @return A String array of size 2, first element being deadlineName, second element being deadlineString.
     */
    public static String[] parseDeadlineCommand(String userCommandText) {
        String[] twoParts = userCommandText.split ("/by ");
        String deadlineName = extractSecondWordOnwards(twoParts[0]);
        twoParts[0] = deadlineName;
        if (deadlineName.length() == 0) {
            throw new DukeException("Empty Description");
        } else if (twoParts.length != 2) {
            throw new DukeException("Usage: deadline {taskName} /by {yyyy-MM-dd HHmm}");
        }
        return twoParts;
    }

    /**
     * Parses an event command string to return a String array, first element being the eventName,
     * second element being eventStart, third element being eventEnd.
     *
     * @param userCommandText The unprocessed command text from the user to add an event.
     * @return A String array of size 3, first element being the eventName,
     *         second element being eventStart, third element being eventEnd
     */
    public static String[] parseEventCommand(String userCommandText) {
        String[] threeParts = userCommandText.split("/");
        String eventName = extractSecondWordOnwards(threeParts[0]);
        threeParts[0] = eventName;
        if (eventName.length() == 0) {
            throw new DukeException("Empty Description");
        }
        if (threeParts.length != 3) {
            throw new DukeException("Incorrect format for event."
                                        + "\nExpected usage: "
                                            + "event {eventName} /from {eventStart} /to {eventEnd}");
        }
        String eventStart = extractSecondWordOnwards(threeParts[1]);
        threeParts[1] = eventStart;
        String eventEnd = extractSecondWordOnwards(threeParts[2]);
        threeParts[2] = eventEnd;
        if (eventStart.length() == 0 || eventEnd.length() == 0) {
            throw new DukeException("Both event start and end date times must be specified.");
        }
        return threeParts;
    }

    /**
     * Parses any id-related command (e.g., delete, mark, unmark tasks) to return the id given the array of inputWords.
     *
     * @param inputWords The array containing the words that were separated by a whitespace character.
     * @return The desired id based on the text provided.
     */
    public static int parseIdCommand(String[] inputWords) {
        int id;
        try {
            id = Integer.valueOf(inputWords[1]) - 1;
        } catch (RuntimeException e) {
            throw new DukeException("Expected usage: mark {id}");
        }
        return id;
    }
}
