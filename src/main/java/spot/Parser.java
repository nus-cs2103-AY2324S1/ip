package spot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import spot.command.AddDeadlineCommand;
import spot.command.AddEventCommand;
import spot.command.AddToDoCommand;
import spot.command.Command;
import spot.command.DeleteCommand;
import spot.command.ExitCommand;
import spot.command.FindCommand;
import spot.command.ListCommand;
import spot.command.ListTasksOnCommand;
import spot.command.MarkCommand;
import spot.command.UnmarkCommand;
import spot.exception.SpotException;


/**
 * Represents a parser to parse commands and dates.
 */
public class Parser {

    /**
     * Returns command corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting command.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseCommand(String input) throws SpotException {
        if (input.startsWith("list tasks on")) {
            return parseListTasksOnCommand(input);
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.startsWith("mark")) {
            return parseMarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return parseUnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return parseAddToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseAddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseAddEventCommand(input);
        } else if (input.startsWith("bye")) {
            return new ExitCommand();
        } else {
            throw new SpotException("Spot doesn't know what command that is!");
        }
    }

    /**
     * Returns ListTasksOnCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting ListTasksOnCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseListTasksOnCommand(String input) throws SpotException {
        String minimumInput = "list tasks on";
        int minimumInputLength = minimumInput.length();
        try {
            String d = input.substring(minimumInputLength).trim();
            LocalDate date = Parser.parseDate(d);
            return new ListTasksOnCommand(date);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks you might've "
                    + "forgotten to add a date!");
        }
    }

    /**
     * Returns FindCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting FindCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseFindCommand(String input) throws SpotException {
        String minimumInput = "find";
        int minimumInputLength = minimumInput.length();
        try {
            String keyword = input.substring(minimumInputLength).trim();
            if (keyword.isEmpty()) {
                throw new SpotException("Spot doesn't know what keyword you're searching for!");
            }
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot doesn't know what keyword you're searching for!");
        }
    }

    /**
     * Returns MarkCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting MarkCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseMarkCommand(String input) throws SpotException {
        String minimumInput = "mark";
        int minimumInputLength = minimumInput.length();
        try {
            int position = Integer.parseInt(input.substring(minimumInputLength).trim());
            return new MarkCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot doesn't know which task to mark as done!");
        }
    }

    /**
     * Returns UnmarkCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting UnmarkCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUnmarkCommand(String input) throws SpotException {
        String minimumInput = "unmark";
        int minimumInputLength = minimumInput.length();
        try {
            int position = Integer.parseInt(input.substring(minimumInputLength).trim());
            return new UnmarkCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot doesn't know which task to mark as not done!");
        }
    }

    /**
     * Returns DeleteCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting DeleteCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseDeleteCommand(String input) throws SpotException {
        String minimumInput = "delete";
        int minimumInputLength = minimumInput.length();
        try {
            int position = Integer.parseInt(input.substring(minimumInputLength).trim());
            return new DeleteCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot doesn't know which task to delete!");
        }
    }

    /**
     * Returns AddToDoCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting AddToDoCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseAddToDoCommand(String input) throws SpotException {
        String minimumInput = "todo";
        int minimumInputLength = minimumInput.length();
        try {
            String description = input.substring(minimumInputLength).trim();
            if (description.isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            return new AddToDoCommand(description);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot wonders if you've "
                    + "forgotten the description?");
        }
    }

    /**
     * Returns AddDeadlineCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting AddDeadlineCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseAddDeadlineCommand(String input) throws SpotException {
        String minimumInput = "deadline";
        int minimumInputLength = minimumInput.length();
        try {
            String[] keywords = input.substring(minimumInputLength).trim().split("/by");
            if (keywords.length == 0 || keywords[0].isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            if (keywords.length < 2) {
                throw new SpotException("Spot thinks you're missing a deadline!");
            }
            String description = keywords[0].trim();
            LocalDate deadline = Parser.parseDate(keywords[1].trim());
            return new AddDeadlineCommand(description, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot needs more details than that!");
        }
    }

    /**
     * Returns AddEventCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting AddEventCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseAddEventCommand(String input) throws SpotException {
        String minimumInput = "event";
        int minimumInputLength = minimumInput.length();
        try {
            String[] keywords = input.substring(minimumInputLength).trim().split("/from|/to");
            if (keywords.length == 0 || keywords[0].isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            if (keywords.length < 3 || keywords[1].isBlank()
                    || keywords[2].isEmpty()) {
                throw new SpotException("Spot can't find a start time"
                        + " and/or an end time!");
            }
            String description = keywords[0].trim();
            LocalDate start = Parser.parseDate(keywords[1].trim());
            LocalDate end = Parser.parseDate(keywords[2].trim());
            if (start.isAfter(end)) {
                throw new SpotException("Spot thinks the start date of your event"
                        + "cannot be after the end date!");
            }
            return new AddEventCommand(description, start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot needs more details than that!");
        }
    }

    /**
     * Returns LocalDate object corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting LocalDate object.
     * @throws SpotException  If input string is an invalid date.
     */
    public static LocalDate parseDate(String input) throws SpotException {
        try {
            return LocalDate.parse(input,
                    DateTimeFormatter.ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new SpotException("This doesn't seem like a valid date to Spot!"
                    + "Please make sure your date is given in this format: dd-mm-yyyy");
        }
    }

}
