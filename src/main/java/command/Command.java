package command;

import java.time.LocalDate;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofException;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;
import woof.Woof;
/**
 * The `Command` class is an abstract class representing a command in the application.
 * All specific command classes should inherit from this class and implement the `execute` method.
 */
public abstract class Command {
    /**
     * The raw command entered by the user.
     */
    private final String rawCommand;

    /**
     * Constructs a new `Command` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public Command(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        this.rawCommand = rawCommand;
    }

    /**
     * Executes the command. Subclasses must implement this method to define their behavior.
     *
     * @param taskList The task list to perform the command on.
     */
    public abstract String execute(TaskList taskList);

    /**
     * Parses a date string received by the application using the input formatter.
     *
     * @param string The date string to be parsed.
     * @return The parsed LocalDate.
     */
    public static LocalDate parseDateTimeIn(String string) {
        return Woof.parseDateTimeIn(string);
    }

    /**
     * Gets the raw command entered by the user.
     *
     * @return The raw command string.
     */
    public String getRawCommand() {
        return this.rawCommand;
    }

    /**
     * Checks if the command is a "bye" command.
     *
     * @return `true` if it's a "bye" command, `false` otherwise.
     */
    public boolean isByeCommand() {
        return CommandWord.commandWordToValueMap(Parser.getArgs(rawCommand)[0]).equals(CommandWord.BYE);
    }

    /**
     * Validates the length of the input string array `args` against the specified `length`. If the length of the array
     * does not match the expected length, a `WoofInvalidCommandException` is thrown with a message indicating the
     * invalid number of arguments for the given `commandWord`.
     *
     * @param commandWord   The command word associated with the operation for which the arguments are being validated.
     * @param args          The input string array to be validated.
     * @param correctLength The expected length of the `args` array.
     *
     * @throws WoofInvalidCommandException If the length of `args` does not match the expected `length`.
     */
    public static void validateArgsLengthEquals(CommandWord commandWord, String[] args, int correctLength) {
        assert commandWord != null : "command word cannot be null";
        assert args != null : "args cannot be null";
        assert correctLength >= 0 : "correct length must more than or equals to 0";

        if (args.length != correctLength) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.toFormattedValue(
                    commandWord.toValue()
                )
            );
        }
    }

    /**
     * Validates the length of the input string array `args` against the specified `length`. If the length
     * of the array matches the unexpected length, a `WoofInvalidCommandException` is thrown with
     * a message indicating the invalid number of arguments for the given `commandWord`.
     *
     * @param commandWord      The command word associated with the operation for which the arguments are being
     *                         validated.
     * @param args             The input string array to be validated.
     * @param unexpectedLength The expected length of the `args` array.
     *
     * @throws WoofInvalidCommandException If the length of `args` does not match the expected `length`.
     */
    public static void validateArgsLengthNotEquals(CommandWord commandWord, String[] args, int unexpectedLength) {
        assert commandWord != null : "command word cannot be null";
        assert args != null : "args cannot be null";

        if (args.length == unexpectedLength) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.toFormattedValue(
                    commandWord.toValue()
                )
            );
        }
    }

    /**
     * Validates the length of the input string array `args` against the specified `length`. If the length
     * of the array less than (not more than equal) to the expected length, a `WoofInvalidCommandException` is thrown
     * with a message indicating the invalid number of arguments for the given `commandWord`.
     *
     * @param commandWord The command word associated with the operation for which the arguments are being validated.
     * @param args        The input string array to be validated.
     * @param minLength   The minimium length of the `args` array.
     *
     * @throws WoofInvalidCommandException If the length of `args` does not match the expected `length`.
     */
    public static void validateArgsLengthMoreThanEquals(CommandWord commandWord, String[] args, int minLength) {
        assert commandWord != null : "command word cannot be null";
        assert args != null : "args cannot be null";

        if (args.length < minLength) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.toFormattedValue(
                    commandWord.toValue()
                )
            );
        }
    }

    /**
     * Validates that none of the provided arguments are null. If any of the arguments are null,
     * a `WoofInvalidCommandException` is thrown with a message indicating null arguments for
     * the given `commandWord`.
     *
     * @param commandWord The command word associated with the operation for which the arguments are being validated.
     * @param args        The arguments to check for null values.
     *
     * @throws WoofInvalidCommandException If any of the arguments are null.
     */
    public static void validateNotNullArgs(CommandWord commandWord, String... args) {
        assert commandWord != null : "command word cannot be null";
        assert args != null : "args cannot be null";

        for (String arg : args) {
            if (arg == null) {
                throw new WoofInvalidCommandException(
                    ExceptionMessage.NULL_ARGUMENT.toFormattedValue(
                        commandWord.toValue()
                    )
                );
            }
        }
    }

    /**
     * Validates that none of the provided arguments are empty (have zero length). If any of the arguments are empty,
     * a `WoofInvalidCommandException` is thrown with a message indicating empty arguments for the given `commandWord`.
     *
     * @param commandWord The command word associated with the operation for which the arguments are being validated.
     * @param args        The arguments to check for empty values.
     *
     * @throws WoofInvalidCommandException If any of the arguments are empty.
     */
    public static void validateNotEmptyArgs(CommandWord commandWord, String... args) {
        assert commandWord != null : "command word cannot be null";
        assert args != null : "args cannot be null";

        for (String arg : args) {
            if (arg.isEmpty()) {
                throw new WoofInvalidCommandException(
                    ExceptionMessage.EMPTY_ARGUMENT.toFormattedValue(
                        commandWord.toValue()
                    )
                );
            }
        }
    }

    /**
     * Validates that the argument provided matches the expected `CommandWord`. If it doesn't match,
     * a `WoofInvalidCommandException` is thrown with a message indicating an invalid command word for
     * the expected `commandWord`.
     *
     * @param commandWord The command word associated with the operation for which the arguments are being validated.
     * @param arg         The argument to check for the command word.
     *
     * @throws WoofInvalidCommandException If the first argument doesn't match the expected `CommandWord`.
     */
    public static void validateCommandWord(CommandWord commandWord, String arg) {
        assert commandWord != null : "command word cannot be null";
        assert arg != null : "arg cannot be null";

        if (!CommandWord.commandWordToValueMap(arg).equals(commandWord)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.toFormattedValue(
                    commandWord.toValue()
                )
            );
        }
    }

    /**
     * Validates a date and time string against a specific date and time format. The format is determined
     * by the `getDateTimeFormatter()` method.
     *
     * @param string The date and time string to validate.
     *
     * @throws WoofInvalidCommandException If the string is null or doesn't match the expected date and time format.
     */
    public static void validateDateTime(String string) {
        assert string != null : "datetime string cannot be null";

        try {
            Woof.validateInDateTime(string);
        } catch (WoofException e) {
            throw new WoofInvalidCommandException(ExceptionMessage.INVALID_DATE_TIME_FORMAT.toFormattedValue(string));
        }
    }

    /**
     * Validates a task index string to ensure it is a valid index for tasks in the application.
     * It delegates the validation to the `TaskList.validateTaskIndex` method and handles any exceptions
     * by throwing a `WoofInvalidCommandException` with an appropriate error message.
     *
     * @param string The task index string to validate.
     *
     * @throws WoofInvalidCommandException If the task index is invalid, or if there's a problem during validation.
     */
    public static void validateTaskIndex(TaskList taskList, String string) {
        assert string != null : "task index string cannot be null";
        TaskList.validateTaskIndex(string, taskList);
    }

    /**
     * Validates that a start date is before an end date.
     *
     * @param startDateStr The start date string to validate.
     * @param endDateStr   The end date string to validate.
     *
     * @throws WoofInvalidCommandException If the start date is not before the end date.
     */
    public static void validateStartDateBeforeEndDate(String startDateStr, String endDateStr) {
        assert startDateStr != null : "Start date cannot be null";
        assert endDateStr != null : "End date cannot be null";

        validateDateTime(startDateStr);
        validateDateTime(endDateStr);
        LocalDate startDate = parseDateTimeIn(startDateStr);
        LocalDate endDate = parseDateTimeIn(endDateStr);

        if (endDate.isBefore(startDate)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.START_DATE_AFTER_END_DATE.toFormattedValue(startDateStr, endDateStr)
            );
        }
    }
}
