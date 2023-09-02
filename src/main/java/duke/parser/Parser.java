package duke.parser;

import duke.command.*;
import duke.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
     * Parses user input and generates the appropriate command for Duke.
     *
     * @param input The user's input command.
     * @return The corresponding Command object based on the user input.
     * @throws DukeInvalidArgumentException If the input does not match any valid command format.
     * @throws DukeInvalidDateFormatException If the input contains an invalid date format.
     * @throws DukeEndDateBeforeStartDateException If an event's end date is before its start date.
     */
    public static Command parseUserInput(String input) throws DukeInvalidArgumentException,
            DukeInvalidDateFormatException, DukeEndDateBeforeStartDateException {
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
            return new ExitCommand();
        } else if (input.matches(listPattern)) {
            return new ListCommand();
        } else if (input.matches(helpPattern)) {
            return new HelpCommand();
        } else {
            Matcher matcher;

            matcher = Pattern.compile(todoPattern).matcher(input);
            if (matcher.matches()) {
                String description = matcher.group(1);
                return new TodoCommand(description);
            }

            matcher = Pattern.compile(deadlinePattern).matcher(input);
            if (matcher.matches()) {
                String description = matcher.group(1);
                LocalDate by;
                try {
                    by = LocalDate.parse(matcher.group(2));
                } catch (DateTimeParseException e) {
                    throw new DukeInvalidDateFormatException();
                }
                return new DeadlineCommand(description, by);
            }

            matcher = Pattern.compile(eventPattern).matcher(input);
            if (matcher.matches()) {
                String description = matcher.group(1);
                LocalDate from;
                LocalDate to;
                try {
                    from = LocalDate.parse(matcher.group(2));
                    to = LocalDate.parse(matcher.group(3));
                    if (to.isBefore(from)) {
                        throw new DukeEndDateBeforeStartDateException();
                    }
                } catch (DateTimeParseException e) {
                    throw new DukeInvalidDateFormatException();
                }
                return new EventCommand(description, from, to);
            }

            matcher = Pattern.compile(markPattern).matcher(input);
            if (matcher.matches()) {
                int index;
                try {
                    index = Integer.parseInt(matcher.group(1));
                } catch (NumberFormatException e) {
                    throw new DukeInvalidArgumentException();
                }
                return new MarkCommand(index);
            }

            matcher = Pattern.compile(unmarkPattern).matcher(input);
            if (matcher.matches()) {
                int index;
                try {
                    index = Integer.parseInt(matcher.group(1));
                } catch (NumberFormatException e) {
                    throw new DukeInvalidArgumentException();
                }
                return new UnmarkCommand(index);
            }

            matcher = Pattern.compile(deletePattern).matcher(input);
            if (matcher.matches()) {
                int index;
                try {
                    index = Integer.parseInt(matcher.group(1));
                } catch (NumberFormatException e) {
                    throw new DukeInvalidArgumentException();
                }
                return new DeleteCommand(index);
            }

            matcher = Pattern.compile(findPattern).matcher(input);
            if (matcher.matches()) {
                return new FindCommand(matcher.group(1));
            }
        }
        throw new DukeInvalidArgumentException();
    }
}
