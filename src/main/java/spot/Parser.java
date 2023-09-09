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
            if (input.length() <= 14) {
                throw new SpotException("Spot thinks you might've "
                        + "forgotten to add a date!");
            }
            String d = input.substring(14);
            LocalDate date = Parser.parseDate(d);
            return new ListTasksOnCommand(date);
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            if (input.length() <= 5) {
                throw new SpotException("Spot needs more details than that!");
            }
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new SpotException("Spot doesn't know what keyword you're searching for!");
            }
            return new FindCommand(keyword);
        } else if (input.startsWith("mark")) {
            if (input.length() <= 5) {
                throw new SpotException("Spot needs more details than that!");
            }
            int position = Integer.parseInt(input.substring(5));
            return new MarkCommand(position);
        } else if (input.startsWith("unmark")) {
            if (input.length() <= 7) {
                throw new SpotException("Spot needs more details than that!");
            }
            int position = Integer.parseInt(input.substring(7));
            return new UnmarkCommand(position);
        } else if (input.startsWith("delete")) {
            if (input.length() <= 7) {
                throw new SpotException("Spot needs more details than that!");
            }
            int position = Integer.parseInt(input.substring(7));
            return new DeleteCommand(position);
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            String description = input.substring(5).trim();
            return new AddToDoCommand(description);
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new SpotException("Spot needs more details than that!");
            }
            String[] keywords = input.substring(9).trim().split("/by");
            if (keywords.length == 0 || keywords[0].trim().isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            if (keywords.length < 2) {
                throw new SpotException("Spot thinks you're missing a deadline!");
            }
            String description = keywords[0].trim();
            LocalDate deadline = Parser.parseDate(keywords[1].trim());
            return new AddDeadlineCommand(description, deadline);
        } else if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new SpotException("Spot needs more details than that!");
            }
            String[] keywords = input.substring(6).trim().split("/from|/to");
            if (keywords.length == 0 || keywords[0].trim().isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            if (keywords.length < 3 || keywords[1].trim().isEmpty()
                    || keywords[2].trim().isEmpty()) {
                throw new SpotException("Spot can't find a start time"
                        + " and/or an end time!");
            }
            String description = keywords[0].trim();
            LocalDate start = Parser.parseDate(keywords[1].trim());
            LocalDate end = Parser.parseDate(keywords[2].trim());
            if (start.isAfter(end)) {
                throw new SpotException("Spot thinks the start date of your event" +
                        "cannot be after the end date!");
            }
            return new AddEventCommand(description, start, end);
        } else if (input.startsWith("bye")) {
            return new ExitCommand();
        } else {
            throw new SpotException("Spot doesn't know what command that is!");
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
