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
import spot.command.UpdateDeadlineCommand;
import spot.command.UpdateDescriptionCommand;
import spot.command.UpdateEndCommand;
import spot.command.UpdateStartCommand;
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
        } else if (input.startsWith("update")) {
            return parseUpdateCommand(input);
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
            String dateString = Parser.getCommandDetails(input, minimumInputLength);
            LocalDate date = Parser.parseDate(dateString);
            return new ListTasksOnCommand(date);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String keyword = Parser.getCommandDetails(input, minimumInputLength);
            if (keyword.isEmpty()) {
                throw new SpotException("Spot doesn't know what keyword you're searching for!");
            }
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String positionString = Parser.getCommandDetails(input, minimumInputLength);
            int position = Integer.parseInt(positionString);
            return new MarkCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String positionString = Parser.getCommandDetails(input, minimumInputLength);
            int position = Integer.parseInt(positionString);
            return new UnmarkCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
        }
    }

    /**
     * Returns update Command corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting Command.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUpdateCommand(String input) throws SpotException {
        String minimumInput = "update";
        int minimumInputLength = minimumInput.length();
        try {
            String commandDetails = Parser.getCommandDetails(input, minimumInputLength);
            if (commandDetails.contains("/description")) {
                return parseUpdateDescriptionCommand(commandDetails);
            } else if (commandDetails.contains("/deadline")) {
                return parseUpdateDeadlineCommand(commandDetails);
            } else if (commandDetails.contains("/start")) {
                return parseUpdateStartCommand(commandDetails);
            } else if (commandDetails.contains("/end")) {
                return parseUpdateEndCommand(commandDetails);
            } else {
                throw new SpotException("Spot doesn't know which field you'd like to edit!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
        }
    }

    /**
     * Returns UpdateDescriptionCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting UpdateDescriptionCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUpdateDescriptionCommand(String input) throws SpotException {
        try {
            String[] keywords = input.split("/description");

            String positionString = keywords[0].trim();
            int position = Integer.parseInt(positionString);

            String description = keywords[1].trim();
            if (description.isEmpty()) {
                throw new SpotException("Spot needs you to provide a valid "
                        + "description for this task!");
            }

            return new UpdateDescriptionCommand(position, description);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
        }
    }

    /**
     * Returns UpdateDeadlineCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting UpdateDeadlineCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUpdateDeadlineCommand(String input) throws SpotException {
        try {
            String[] keywords = input.split("/deadline");

            String positionString = keywords[0].trim();
            int position = Integer.parseInt(positionString);

            String deadlineString = keywords[1];
            LocalDate deadline = Parser.parseDate(deadlineString);

            return new UpdateDeadlineCommand(position, deadline);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
        }
    }

    /**
     * Returns UpdateStartCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting UpdateStartCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUpdateStartCommand(String input) throws SpotException {
        try {
            String[] keywords = input.split("/start");

            String positionString = keywords[0].trim();
            int position = Integer.parseInt(positionString);

            String startString = keywords[1];
            LocalDate start = Parser.parseDate(startString);

            return new UpdateStartCommand(position, start);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
        }
    }

    /**
     * Returns UpdateEndCommand corresponding to the specified input string.
     *
     * @param input Input string to be parsed.
     * @return Resulting UpdateEndCommand.
     * @throws SpotException  If input string is invalid.
     */
    public static Command parseUpdateEndCommand(String input) throws SpotException {
        try {
            String[] keywords = input.split("/end");

            String positionString = keywords[0].trim();
            int position = Integer.parseInt(positionString);

            String endString = keywords[1];
            LocalDate end = Parser.parseDate(endString);

            return new UpdateEndCommand(position, end);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String description = Parser.getCommandDetails(input, minimumInputLength);
            if (description.isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }
            return new AddToDoCommand(description);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String truncatedInput = Parser.getCommandDetails(input, minimumInputLength);
            String[] keywords = truncatedInput.split("/by");

            String description = keywords[0].trim();
            if (description.isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }

            String deadlineString = keywords[1];
            LocalDate deadline = Parser.parseDate(deadlineString);

            return new AddDeadlineCommand(description, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String truncatedInput = Parser.getCommandDetails(input, minimumInputLength);
            String[] keywords = truncatedInput.split("/from|/to");

            String description = keywords[0].trim();
            if (description.isEmpty()) {
                throw new SpotException("Spot wonders if you've "
                        + "forgotten the description?");
            }

            String startString = keywords[1];
            LocalDate start = Parser.parseDate(startString);

            String endString = keywords[2];
            LocalDate end = Parser.parseDate(endString);

            if (start.isAfter(end)) {
                throw new SpotException("Spot thinks the start date of your event "
                        + "cannot be after the end date!");
            }

            return new AddEventCommand(description, start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String truncatedInput = Parser.getCommandDetails(input, minimumInputLength);
            int position = Integer.parseInt(truncatedInput);
            return new DeleteCommand(position);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new SpotException("Spot thinks your command is "
                    + "in the wrong format!");
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
            String trimmedInput = input.trim();
            return LocalDate.parse(trimmedInput,
                    DateTimeFormatter.ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new SpotException("This doesn't seem like a valid date to Spot! "
                    + "Please make sure your date is given in this format: dd-mm-yyyy");
        }
    }

    /**
     * Retrieves command details from an input starting from a given index.
     *
     * @param input Input string.
     * @param startingIndex The starting index from which to retrieve command details.
     * @return Resulting String containing command details.
     */
    public static String getCommandDetails(String input, int startingIndex) {
        return input.substring(startingIndex).trim();
    }

}
