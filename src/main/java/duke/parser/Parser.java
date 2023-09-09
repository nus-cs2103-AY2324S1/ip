package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeDatabaseInvalidEntryException;
import duke.exception.DukeEndDateBeforeStartDateException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidDateFormatException;

/**
 * Parses user input and database entries.
 */
public class Parser {

    final static String DB_PATTERN = "\\[([A-Z])\\]\\[(.)\\] (.+?)(?: \\(by: (.+?)\\)| \\(from: (.+?) to: (.+?)\\))?";
    final static String BYE_PATTERN = "(?i)bye";
    final static String LIST_PATTERN = "(?i)list";
    final static String HELP_PATTERN = "(?i)help";
    final static String TODO_PATTERN = "(?i)todo (.+)";
    final static String DEADLINE_PATTERN = "(?i)deadline (.+) /by (.+)";
    final static String EVENT_PATTERN = "(?i)event (.+) /from (.+) /to (.+)";
    final static String MARK_PATTERN = "(?i)mark (\\d+)";
    final static String UNMARK_PATTERN = "(?i)unmark (\\d+)";
    final static String DELETE_PATTERN = "(?i)delete (\\d+)";
    final static String FIND_PATTERN = "(?i)find ([a-zA-Z0-9]+)";
    final static String[] PATTERNS = {BYE_PATTERN, LIST_PATTERN, HELP_PATTERN, TODO_PATTERN, DEADLINE_PATTERN,
            EVENT_PATTERN, MARK_PATTERN, UNMARK_PATTERN, DELETE_PATTERN, FIND_PATTERN};

    /**
     * Parses a database entry and extracts relevant information.
     *
     * @param entry The database entry to be parsed.
     * @return An ArrayList containing the elements extracted from the database entry.
     * @throws DukeDatabaseInvalidEntryException If the database entry format is invalid.
     */
    public static ArrayList<String> parseDatabaseEntry(String entry) throws DukeDatabaseInvalidEntryException {
        ArrayList<String> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile(DB_PATTERN);
        Matcher matcher = pattern.matcher(entry);

        if (!matcher.matches()) {
            throw new DukeDatabaseInvalidEntryException();
        }

        for (int i = 1; i <= 6; i ++) {
            if (matcher.group(i) != null) {
                elements.add(matcher.group(i));
            }
        }

        return elements;
    }

    /**
     * Parses a TODO command and extracts the description.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return A TodoCommand object with the extracted description.
     */
    private static TodoCommand parseTodoCommand(Matcher matcher) {
        String description = matcher.group(1);
        return new TodoCommand(description);
    }

    /**
     * Parses a DEADLINE command and extracts the description and deadline date.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return A DeadlineCommand object with the extracted description and deadline date.
     * @throws DukeInvalidDateFormatException If the date format is invalid.
     */
    private static DeadlineCommand parseDeadlineCommand(Matcher matcher) throws DukeInvalidDateFormatException {
        try {
            String description = matcher.group(1);
            LocalDate by = LocalDate.parse(matcher.group(2));
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Parses an EVENT command and extracts the description, start date, and end date.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return An EventCommand object with the extracted information.
     * @throws DukeInvalidDateFormatException If the date format is invalid.
     * @throws DukeEndDateBeforeStartDateException If the end date is before the start date.
     */
    private static EventCommand parseEventCommand(Matcher matcher)
            throws DukeInvalidDateFormatException, DukeEndDateBeforeStartDateException {
        try {
            String description = matcher.group(1);
            LocalDate from = LocalDate.parse(matcher.group(2));
            LocalDate to = LocalDate.parse(matcher.group(3));

            if (to.isBefore(from)) {
                throw new DukeEndDateBeforeStartDateException();
            }

            return new EventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Parses a MARK command and extracts the task index.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return A MarkCommand object with the extracted task index.
     * @throws DukeInvalidArgumentException If the task index is invalid.
     */
    private static MarkCommand parseMarkCommand(Matcher matcher) throws DukeInvalidArgumentException {
        try {
            int index = Integer.parseInt(matcher.group(1));
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        }
    }

    /**
     * Parses an UNMARK command and extracts the task index.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return An UnmarkCommand object with the extracted task index.
     * @throws DukeInvalidArgumentException If the task index is invalid.
     */
    private static UnmarkCommand parseUnmarkCommand(Matcher matcher) throws DukeInvalidArgumentException {
        try {
            int index = Integer.parseInt(matcher.group(1));
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        }
    }

    /**
     * Parses a DELETE command and extracts the task index.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return A DeleteCommand object with the extracted task index.
     * @throws DukeInvalidArgumentException If the task index is invalid.
     */
    private static DeleteCommand parseDeleteCommand(Matcher matcher) throws DukeInvalidArgumentException {
        try {
            int index = Integer.parseInt(matcher.group(1));
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        }
    }

    /**
     * Parses a FIND command and extracts the search keyword.
     *
     * @param matcher The Matcher object containing the parsed input.
     * @return A FindCommand object with the extracted search keyword.
     */
    private static FindCommand parseFindCommand(Matcher matcher) {
        String keyword = matcher.group(1);
        return new FindCommand(keyword);
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
        for (String pattern : PATTERNS) {
            Matcher matcher = Pattern.compile(pattern).matcher(input);
            if (!matcher.matches()) {
                continue;
            }
            if (pattern.equals(BYE_PATTERN)) {
                return new ExitCommand();
            } else if (pattern.equals(LIST_PATTERN)) {
                return new ListCommand();
            } else if (pattern.equals(HELP_PATTERN)) {
                return new HelpCommand();
            } else if (pattern.equals(TODO_PATTERN)) {
                return parseTodoCommand(matcher);
            } else if (pattern.equals(DEADLINE_PATTERN)) {
                return parseDeadlineCommand(matcher);
            } else if (pattern.equals(EVENT_PATTERN)) {
                return parseEventCommand(matcher);
            } else if (pattern.equals(MARK_PATTERN)) {
                return parseMarkCommand(matcher);
            } else if (pattern.equals(UNMARK_PATTERN)) {
                return parseUnmarkCommand(matcher);
            } else if (pattern.equals(DELETE_PATTERN)) {
                return parseDeleteCommand(matcher);
            } else if (pattern.equals(FIND_PATTERN)) {
                return parseFindCommand(matcher);
            }
        }

        throw new DukeInvalidArgumentException();
    }

}
