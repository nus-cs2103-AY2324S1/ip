package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the part of the chatbot that deals with making sense of the command.
 */
public class Parser {
    /**
     * Returns a command that is specified in the user input.
     * 
     * @param command Command that the user keys in.
     * @return the specified command in the user's input.
     * @throws DukeException  If input is invalid.
     */
    public static Command parse(String command) throws DukeException {
        if (isExitCommand(command)) {
            return new ExitCommand();
        } else if (isListCommand(command)) {
            return new ListCommand();
        } else if (isMarkCommand(command)) {
            String input = command.replaceFirst("mark", "").trim();
            int index = validateMarkIndex(input);
            return new MarkCommand(index);
        } else if (isUnMarkCommand(command)) {
            String input = command.replaceFirst("unmark", "").trim();
            int index = validateMarkIndex(input);
            return new UnMarkCommand(index);
        } else if (isAddToDoCommand(command)) {
            String todo = command.replaceFirst("todo", "").trim();
            validateToDo(todo);
            return new AddToDoCommand(todo);
        } else if (isAddDeadlineCommand(command)) {
            String[] deadline = validateDeadline(command);
            if (isDate(deadline[1])) {
                LocalDateTime deadlineDate = parseDate(deadline[1]);
                return new AddDeadlineCommand(deadline[0], deadlineDate);
            } else {
                return new AddDeadlineCommand(deadline[0], deadline[1]);
            }
        } else if (isAddEventCommand(command)) {
            String[] event = validateEvent(command);
            if (isDate(event[1])) {
                LocalDateTime eventDate = parseDate(event[1]);
                LocalDateTime endTime = parseDate(event[2]);
                return new AddEventCommand(event[0], eventDate, endTime);
            } else {
                return new AddEventCommand(event[0], event[1], event[2]);
            }
        } else if (isDeleteCommand(command)) {
            String input = command.replaceFirst("delete", "").trim();
            int index = validateDeleteIndex(input);
            return new DeleteCommand(index);
        } else if (isFindCommand(command)) {
            String keyword = command.replace("find", "").trim();
            validateFindInput(keyword);
            return new FindCommand(keyword);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the date in the form of a LocalDateTime.
     * 
     * @param date Date of the specific Event or Deadline.
     * @return Date in the form of LocalDateTime.
     * @throws DukeException If date is invalid.
     */
    private static LocalDateTime parseDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    /**
     * Returns a boolean that represents if the input is a date.
     * 
     * @param input Date that user keyed in.
     * @return True if it is a date, false if otherwise.
     */
    private static boolean isDate(String input) {
        String[] splitInput = input.split("-");
        if (splitInput.length != 3) {
            return false;
        } else if (isNotNumeric(splitInput[0])) {
            return false;
        } else if (isNotNumeric(splitInput[1])) {
            return false;
        }
        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2) {
            return false;
        } else if (isNotNumeric(yearAndTime[0])) { 
            return false;
        } else if (isNotNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    /**
     * Returns the index to be marked.
     * 
     * @param markInput The index to be checked if it is valid.
     * @return The index to be marked.
     * @throws DukeException If index is invalid.
     */
    private static int validateMarkIndex(String markInput) throws DukeException {
        return validateMarkOrDeleteIndex(markInput);
    }

    /**
     * Return the index to be deleted.
     * 
     * @param deleteInput The index to be checked if it is valid.
     * @return The index to be deleted.
     * @throws DukeException If index is invalid.
     */
    private static int validateDeleteIndex(String deleteInput) throws DukeException {
        return validateMarkOrDeleteIndex(deleteInput);
    }

    /**
     * Returns the index in the form of an integer.
     * 
     * @param input The index to be changed into an integer.
     * @return Integer that represents the index.
     * @throws DukeException If input is invalid.
     */
    private static int validateMarkOrDeleteIndex(String input) throws DukeException {
        if (input.isEmpty() || isNotNumeric(input)) {
            throw new DukeException("OOPS!! The index to edit is not valid!");
        }
        return Integer.parseInt(input);
    }

    /**
     * Returns a boolean representing if the input is a number.
     * 
     * @param input Input to check if it is a number.
     * @return True if input is not a number, false if otherwise.
     */
    private static boolean isNotNumeric(String input) {
        return !input.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Checks if ToDo task is valid.
     * 
     * @param todoString Description of the ToDo.
     * @throws DukeException If Description is incomplete.
     */
    private static void validateToDo(String todoString) throws DukeException {
        if (todoString.isEmpty()) {
            throw new DukeException("OOPS!! The description of a ToDo cannot be empty!");
        }
    }

    /**
     * Returns an array of String representing the various components of an Event.
     * 
     * @param input String input of the event.
     * @return an array of String objects that represent an Event.
     * @throws DukeException If input is invalid or missing details.
     */
    private static String[] validateEvent(String input) throws DukeException {
        String description = input.replace("event ", "");
        String[] details = description.split(" /");
        if (details.length != 3) {
            throw new DukeException("OOPS!! You need to include the details!");
        }
        details[1] = details[1].replace("from ", "");
        details[2] = details[2].replace("to ", "");
        if (details[1].isEmpty() || details[2].isEmpty()) {
            throw new DukeException("OOPS! Please make sure the dates are not empty!");
        }
        return details;
    }

    /**
     * Returns an array of String objects that represents a Deadline task.
     * 
     * @param input Input for a Deadline.
     * @return an array of String objects that represents a Deadline.
     * @throws DukeException If input is invalid or incomplete.
     */
    private static String[] validateDeadline(String input) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeException("OOPS!! You need to include the deadline!");
        }
        String description = input.replace("deadline ", "");
        String[] details = description.split(" /by ");
        if (details.length != 2) {
            throw new DukeException("OOPS!! You need to include the deadline!");
        }
        return details;
    }

    /**
     * Checks if the input is valid.
     * 
     * @param input The keyword to be found.
     * @throws DukeException If there is no keyword.
     */
    private static void validateFindInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("OOPS!! The filter is not supposed to be empty!");
        }
    }

    /**
     * Returns a boolean that represents whether the command is an exit command.
     * 
     * @param input Command to be checked.
     * @return True if it is an exit command, false if otherwise.
     */
    private static boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    /**
     * Returns a boolean that represents whether the command is a list command.
     * 
     * @param input Command to be checked.
     * @return True if it is a list command, false if otherwise.
     */
    private static boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Returns a boolean that represents whether the command is a mark command.
     * 
     * @param input Command to be checked.
     * @return True if it is a mark command, false if otherwise.
     */
    private static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    /**
     * Returns a boolean that represents whether the command is a add ToDo command.
     * 
     * @param input Command to be checked.
     * @return True if it is a add ToDo command, false if otherwise.
     */
    private static boolean isAddToDoCommand(String input) {
        return input.startsWith("todo");
    }

    /**
     * Returns a boolean that represents whether the command is an add deadline command.
     * 
     * @param input Command to be checked.
     * @return True if it is an add deadline command, false if otherwise.
     */
    private static boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    /**
     * Returns a boolean that represents whether the command is an add event command.
     * 
     * @param input Command to be checked.
     * @return True if it is an add event command, false if otherwise.
     */
    private static boolean isAddEventCommand(String input) {
        return input.startsWith("event");
    }

    /**
     * Returns a boolean that represents whether the command is a delete command.
     * 
     * @param input Command to be checked.
     * @return True if it is a delete command, false if otherwise.
     */
    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    /**
     * Returns a boolean that represents whether the command is an unmark command.
     * 
     * @param input Command to be checked.
     * @return True if it is an unmark command, false if otherwise.
     */
    private static boolean isUnMarkCommand(String input) {
        return input.startsWith("unmark");
    }

    /**
     * Returns a boolean that represents whether the command is a find command.
     * 
     * @param input Command to be checked.
     * @return True if it is a find command, false if otherwise.
     */
    private static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }
}
