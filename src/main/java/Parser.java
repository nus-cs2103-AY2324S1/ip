import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String input) {
        String commandWord = input.split(" ")[0].toLowerCase(); // Extract the first word (the command word)

        switch (commandWord) {
            case "bye" :
                return new ByeCommand();
            case "list" :
                return new ListCommand();
            case "todo" :
                try {
                    String taskName = input.split(" ", 2)[1];
                    //add item into list
                    ToDo task = new ToDo(taskName);
                    return new AddCommand(task);
                } catch(ArrayIndexOutOfBoundsException e) {
                    return new ErrorCommand("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline" :
                try {
                    String taskName = input.split(" /by ", 2)[0].split(" ", 2)[1];

                    // ArrayList containing possible formats of Date and Time.
                    List<String> formatStrings = Arrays.asList(
                            "yyyy-MM-dd HH:mm",
                            "dd/MM/yyyy HH:mm",
                            "MM-dd-yyyy HH:mm"
                            // Add other formats here
                    );
                    String dateInput = input.split(" /by ", 2)[1];
                    String dueDate = null;

                    // Try parsing with different formats
                    for (String formatString : formatStrings) {
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
                        throw new DateTimeParseException("Invalid date/time format", dateInput, 0);
                    }

                    // add item into list
                    Deadline task = new Deadline(taskName, dueDate);
                    return new AddCommand(task);
                } catch ( ArrayIndexOutOfBoundsException e) {
                    if (input.split(" ").length == 1) {
                        return new ErrorCommand("☹ OOPS!!! The description of a deadline cannot be empty");
                    } else {
                        return new ErrorCommand("☹ OOPS!!! The description of a deadline does not have \"/by\" specified");
                    }
                } catch (DateTimeParseException e) {
                    return new ErrorCommand("☹ OOPS!!! The format of deadline is wrong, please write it in the correct format");
                }
            case "event" :
                try {

                    String taskName = input.split(" /from | /to ", 3)[0].split(" ", 2)[1];
                    String startDateInput = input.split(" /from | /to ", 3)[1];
                    String dueDateInput = input.split(" /from | /to ", 3)[2];

                    // ArrayList containing possible formats of Date and Time.
                    List<String> formatStrings = Arrays.asList(
                            "yyyy-MM-dd HH:mm",
                            "dd/MM/yyyy HH:mm",
                            "MM-dd-yyyy HH:mm"
                            // Add other formats here
                    );
                    LocalDateTime startDateTime = null;
                    String startDate = null;

                    // Try parsing with different formats for starting date and time input.
                    for (String formatString : formatStrings) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                            startDateTime = LocalDateTime.parse(startDateInput, formatter);
                            startDate = startDateTime.format(formatter);
                            break;  // Stop at the first successful parse
                        } catch (DateTimeParseException e) {
                            // Ignore the exception and try the next format
                        }
                    }

                    // if the input fits none of the format, throw DateTimeParseException
                    if (startDate == null) {
                        throw new DateTimeParseException("Invalid date/time format", startDate, 0);
                    }
                    LocalDateTime dueDateTime = null;
                    String dueDate = null;

                    // Try parsing with different formats for due date and time input.
                    for (String formatString : formatStrings) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                            dueDateTime = LocalDateTime.parse(dueDateInput, formatter);
                            dueDate = dueDateTime.format(formatter);
                            break;  // Stop at the first successful parse
                        } catch (DateTimeParseException e) {
                            // Ignore the exception and try the next format
                        }
                    }

                    // if the input fits none of the format, throw DateTimeParseException
                    if (dueDate == null) {
                        throw new DateTimeParseException("Invalid date/time format", dueDateInput, 0);
                    }

                    // check if start date is before due date.
                    if (startDateTime.isAfter(dueDateTime) || startDateTime.isEqual(dueDateTime)) {
                        throw new IllegalArgumentException();
                    }

                    // add item into list
                    Event task = new Event(taskName, startDate, dueDate);
                    return new AddCommand(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (input.split(" ").length == 1) {
                        return new ErrorCommand("☹ OOPS!!! The description of a event cannot be empty");
                    } else {
                        return new ErrorCommand("☹ OOPS!!! The format of starting date or due date is wrong, please write it in the format correct format");
                    }
                } catch (DateTimeParseException e) {
                    return new ErrorCommand("☹ OOPS!!! The format of starting date or due date is wrong, please write it in the format correct format");
                } catch (IllegalArgumentException e) {
                    return new ErrorCommand("☹ OOPS!!! The starting date cannot be later than the due date.");
                }
            case "delete" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new DeleteCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand("☹ OOPS!!! The task number you entered for deleting is not in the list.");
                } catch (NumberFormatException e) {
                    return new ErrorCommand("☹ OOPS!!! The task number you entered for deleting is invalid. Please enter a number.");
                }
            case "mark" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new MarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand("☹ OOPS!!! The task number you entered for marking is not in the list.");
                }
            case "unmark" :
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    return new UnmarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand("☹ OOPS!!! The task number you entered for marking is not in the list.");
                }
            default:
                return new ErrorCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");


        }
    }
}
