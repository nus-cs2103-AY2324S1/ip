package parser;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.ErrorCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

/**
 * Parser class handles the interpretation of user input into commands that can be executed.
 */
public class Parser {
    // A List containing possible formats of date and time.
    private static final List<String> DATE_STRING_FORMATS = Arrays.asList(
            "yyyy-MM-dd HH:mm",
            "dd/MM/yyyy HH:mm",
            "MM-dd-yyyy HH:mm"
            // Add other formats here
    );

    /**
     * Parses the user input and returns a Command object
     * representing the user's intended action.
     *
     * @param input The full input string entered by the user.
     * @return A Command object representing the action to be taken.
     */
    public static Command parse(String input) {
        // Extracts the first word (the command word) from the input
        String commandWord = input.split(" ")[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ByeCommand();
                // Fallthrough
            case "list":
                return new ListCommand();
                // Fallthrough
            case "todo":
                try {
                    String taskName = input.split(" ", 2)[1];
                    ToDo task = new ToDo(taskName);
                    return new AddCommand(task);
                } catch(ArrayIndexOutOfBoundsException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The description of a todo cannot be empty.");
                }
                // Fallthrough
            case "deadline":
                try {
                    String taskName = input.split(" /by ", 2)[0].split(" ", 2)[1];
                    String dateInput = input.split(" /by ", 2)[1];
                    String dueDate = null;

                    // Try parsing with different formats
                    for (String formatString : DATE_STRING_FORMATS) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                            LocalDateTime dateTime = LocalDateTime.parse(dateInput, formatter);
                            dueDate = dateTime.format(formatter);
                            break;  // Stop at the first successful parse
                        } catch (DateTimeParseException e) {
                            // Ignore the exception and try the next format
                        }
                    }

                    // if the input fits none of the format, throw DateTimeParseException
                    if (dueDate == null) {
                        throw new DateTimeParseException(
                                "Invalid date/time format", dateInput, 0);
                    }

                    Deadline task = new Deadline(taskName, dueDate);
                    return new AddCommand(task);
                } catch ( ArrayIndexOutOfBoundsException e) {
                    if (input.split(" ").length == 1) {
                        return new ErrorCommand(
                                "☹ OOPS!!! The description of a deadline cannot be empty");
                    } else {
                        return new ErrorCommand(
                                "☹ OOPS!!! The description of a deadline "
                                        + "does not have \"/by\" specified");
                    }
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The format of deadline is wrong, "
                                    + "please write it in the correct format");
                }
                // Fallthrough
            case "event":
                try {
                    String taskName = input.split(" /from | /to ", 3)[0].split(" ", 2)[1];
                    String startDateInput = input.split(" /from | /to ", 3)[1];
                    String dueDateInput = input.split(" /from | /to ", 3)[2];
                    LocalDateTime startDateTime = null;
                    LocalDateTime dueDateTime = null;
                    String startDateString = null;
                    String dueDateString = null;

                    // Try parsing with different formats for starting date and time input.
                    for (String formatString : DATE_STRING_FORMATS) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter
                                    .ofPattern(formatString);
                            startDateTime = LocalDateTime.parse(startDateInput, formatter);
                            startDateString = startDateTime.format(formatter);
                            break;  // Stop at the first successful parse
                        } catch (DateTimeParseException e) {
                            // Ignore the exception and try the next format
                        }
                    }

                    // if the input fits none of the format, throw DateTimeParseException
                    if (startDateString == null) {
                        throw new DateTimeParseException(
                                "Invalid date/time format", startDateString, 0);
                    }

                    // Try parsing with different formats for due date and time input.
                    for (String formatString : DATE_STRING_FORMATS) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                            dueDateTime = LocalDateTime.parse(dueDateInput, formatter);
                            dueDateString = dueDateTime.format(formatter);
                            break;  // Stop at the first successful parse
                        } catch (DateTimeParseException e) {
                            // Ignore the exception and try the next format
                        }
                    }

                    // if the input fits none of the format, throw DateTimeParseException
                    if (dueDateString == null) {
                        throw new DateTimeParseException(
                                "Invalid date/time format", dueDateInput, 0);
                    }

                    // check if start date is before due date.
                    if (startDateTime.isAfter(dueDateTime) || startDateTime.isEqual(dueDateTime)) {
                        throw new IllegalArgumentException();
                    }

                    Event task = new Event(taskName, startDateString, dueDateString);
                    return new AddCommand(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (input.split(" ").length == 1) {
                        return new ErrorCommand(
                                "☹ OOPS!!! The description of a event cannot be empty");
                    } else {
                        return new ErrorCommand(
                                "☹ OOPS!!! The format of starting date or due date is wrong,"
                                        + " please write it in the format correct format");
                    }
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The format of starting date or due date is wrong,"
                                    + " please write it in the format correct format");
                } catch (IllegalArgumentException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The starting date "
                                    + "cannot be later than the due date.");
                }
                // Fallthrough
            case "delete" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new DeleteCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The task number you entered "
                                    + "for deleting is not in the list.");
                } catch (NumberFormatException e) {
                    return new ErrorCommand("☹ OOPS!!! The task number you entered "
                            + "for deleting is invalid. Please enter a number.");
                }
                // Fallthrough
            case "mark" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new MarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The task number you "
                                    + "entered for marking is not in the list.");
                }
                // Fallthrough
            case "unmark" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new UnmarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(
                            "☹ OOPS!!! The task number you "
                                    + "entered for marking is not in the list.");
                }
                // Fallthrough
            default:
                return new ErrorCommand(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
