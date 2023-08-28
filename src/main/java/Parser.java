import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    static Command parse(String command) throws DukeException {
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
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static LocalDateTime parseDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    private static boolean isDate(String input) {
        String[] splitInput = input.split("-");
        if (splitInput.length != 3 || isNotNumeric(splitInput[0]) || isNotNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2 || isNotNumeric(yearAndTime[0]) || isNotNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    private static int validateMarkIndex(String markInput) throws DukeException {
        return validateMarkOrDeleteIndex(markInput);
    }

    private static int validateDeleteIndex(String deleteInput) throws DukeException {
        return validateMarkOrDeleteIndex(deleteInput);
    }

    private static int validateMarkOrDeleteIndex(String input) throws DukeException {
        if (input.isEmpty() || isNotNumeric(input)) {
            throw new DukeException("OOPS!! The index to edit is not valid!");
        }
        return Integer.parseInt(input);
    }

    private static boolean isNotNumeric(String input) {
        return !input.matches("-?\\d+(\\.\\d+)?");
    }

    private static void validateToDo(String todoString) throws DukeException {
        if (todoString.isEmpty()) {
            throw new DukeException("OOPS!! The description of a ToDo cannot be empty!");
        }
    }

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

    private static boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    private static boolean isListCommand(String input) {
        return input.equals("list");
    }

    private static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    private static boolean isAddToDoCommand(String input) {
        return input.startsWith("todo");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    private static boolean isAddEventCommand(String input) {
        return input.startsWith("event");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    private static boolean isUnMarkCommand(String input) {
        return input.startsWith("unmark");
    }
}
