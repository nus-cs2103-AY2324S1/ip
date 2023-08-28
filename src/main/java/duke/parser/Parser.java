package duke.parser;

import duke.Command;
import duke.exception.DukeDatabaseInvalidEntryException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeNoSuchCommandException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and database entries.
 */
public class Parser {

    /**
     * Parses a database entry and extracts relevant information.
     *
     * @param entry The database entry to be parsed.
     * @return An ArrayList containing the elements extracted from the database entry.
     * @throws DukeDatabaseInvalidEntryException If the database entry format is invalid.
     */
    public static ArrayList<String> parseDatabaseEntry(String entry) throws DukeDatabaseInvalidEntryException {
        ArrayList<String> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([A-Z])\\]\\[(.)\\] (.+?)(?: \\(by: (.+?)\\)| \\(from: (.+?) to: (.+?)\\))?");
        Matcher matcher = pattern.matcher(entry);

        if (matcher.matches()) {
            elements.add(matcher.group(1));
            elements.add(matcher.group(2));
            elements.add(matcher.group(3));
            if (matcher.group(4) != null) {
                elements.add(matcher.group(4));
            } else if (matcher.group(5) != null && matcher.group(6) != null) {
                elements.add(matcher.group(5));
                elements.add(matcher.group(6));
            }
        } else {
            throw new DukeDatabaseInvalidEntryException();
        }

        return elements;
    }

    /**
     * Parses a Command from the given input String.
     *
     * @param input The user input String.
     * @return The Command enum value corresponding to the parsed command.
     * @throws DukeNoSuchCommandException If the input does not correspond to a known Command.
     */
    public static Command parseCommand(String input) throws DukeNoSuchCommandException {
        String[] inputArr = input.split(" ");
        try {
            return Command.valueOf(inputArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeNoSuchCommandException();
        }
    }

    /**
     * Parses user input and extracts relevant information.
     *
     * @param input The user input String to be parsed.
     * @return An ArrayList containing the elements extracted from the user input String.
     * @throws DukeInvalidArgumentException If the input does not correspond to a known Command.
     */
    public static ArrayList<String> parseUserInput(String input) throws DukeInvalidArgumentException {
        ArrayList<String> result = new ArrayList<>();

        String byePattern = "bye";
        String listPattern = "list";
        String helpPattern = "help";
        String todoPattern = "todo (.+)";
        String deadlinePattern = "deadline (.+) /by (.+)";
        String eventPattern = "event (.+) /from (.+) /to (.+)";
        String markPattern = "mark (\\d+)";
        String unmarkPattern = "unmark (\\d+)";
        String deletePattern = "delete (\\d+)";
        String findPattern = "find ([a-zA-Z0-9]+)";

        if (input.matches(byePattern)) {
            result.add("bye");
        } else if (input.matches(listPattern)) {
            result.add("list");
        } else if (input.matches(helpPattern)) {
            result.add("help");
        } else {
            Matcher matcher;

            matcher = Pattern.compile(todoPattern).matcher(input);
            if (matcher.matches()) {
                result.add("todo");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(deadlinePattern).matcher(input);
            if (matcher.matches()) {
                result.add("deadline");
                result.add(matcher.group(1));
                result.add(matcher.group(2));
            }

            matcher = Pattern.compile(eventPattern).matcher(input);
            if (matcher.matches()) {
                result.add("event");
                result.add(matcher.group(1));
                result.add(matcher.group(2));
                result.add(matcher.group(3));
            }

            matcher = Pattern.compile(markPattern).matcher(input);
            if (matcher.matches()) {
                result.add("mark");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(unmarkPattern).matcher(input);
            if (matcher.matches()) {
                result.add("unmark");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(deletePattern).matcher(input);
            if (matcher.matches()) {
                result.add("delete");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(findPattern).matcher(input);
            if (matcher.matches()) {
                result.add("find");
                result.add(matcher.group(1));
            }
        }
        if (result.isEmpty()) {
            throw new DukeInvalidArgumentException();
        }
        return result;
    }
}