package ekud.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

import ekud.exceptions.EkudException;
import ekud.exceptions.EkudIllegalArgException;
import ekud.exceptions.EkudInvalidCommandException;
import ekud.tasks.TaskList;

/**
 * The Parser class serves as an interface between the user and the chatbot by
 * converting user inputs into commands and arguments that the chatbot understands,
 * before finally executing these commands on the chatbot's TaskList and returning
 * a String response.
 */
public class Parser {
    private static final String INPUT_DATETIME_FORMAT = "d MMM HHmm";
    private static final String SAVED_DATETIME_FORMAT = "dd MMM yyyy h:mm a";

    /**
     * Splits the user's input into command and arguments.
     * @param userInput The given line of raw user input.
     * @return String[] containing the user command and user args.
     */
    public String[] parseInput(String userInput) {
        int firstSpace = userInput.indexOf(' ');
        String userCommand = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        String userArgs = firstSpace == -1 ? "" : userInput.substring(firstSpace + 1);
        return new String[]{userCommand, userArgs};
    }

    /**
     * Core function for parsing user arguments based on the command and handling any invalid
     * arguments, before executing the command and returning a String response.
     * @param userInputs The inputs given by the user which includes the command and respective args.
     * @return String response message for the user.
     * @throws EkudException Either invalid commands or illegal arguments for the commands.
     */
    public String parseAndExecute(TaskList taskList, String ... userInputs) throws EkudException {
        String userCommand = userInputs[0];
        String userArgs = userInputs[1];
        Command command = Command.getCommand(userCommand); // Command enum
        if (command == null) {
            throw new EkudInvalidCommandException(String.format(
                    "Command '%s' not found :(",
                    userCommand));
        }
        switch (command) {
        case SHOWTASKS:
            return taskList.showTasks();
        case MARKTASKASDONE:
            int doneTaskNum = this.parseTaskNum(userArgs);
            return taskList.markTaskAsDone(doneTaskNum - 1);
        case MARKTASKASNOTDONE:
            int notDoneTaskNum = this.parseTaskNum(userArgs);
            return taskList.markTaskAsNotDone(notDoneTaskNum - 1);
        case ADDTODO:
            if (userArgs.isBlank()) { // isBlank() checks if string is all whitespace
                throw new EkudIllegalArgException("Description shouldn't be empty :(");
            }
            return taskList.addToDo(userArgs);
        case ADDDEADLINE:
            try {
                String[] deadlineArgs = userArgs.split(" /by ");
                String description = deadlineArgs[0];
                LocalDateTime dateTime = this.parseDateTime(deadlineArgs[1]);
                if (description.isBlank()) {
                    throw new EkudIllegalArgException("Description shouldn't be empty :(");
                }
                return taskList.addDeadline(description, dateTime);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new EkudIllegalArgException(String.format(
                        "Deadline formatted wrongly\n"
                        + "-> Please ensure 'deadline <description> /by <%s>' is followed\n"
                        + "-> For example: deadline finish quiz /by 3 Jun 1830",
                        INPUT_DATETIME_FORMAT));
            }
        case ADDEVENT:
            try {
                String[] eventArgs = userArgs.split(" /from ");
                String[] timings = eventArgs[1].split(" /to ");
                String description = eventArgs[0];
                if (description.isBlank() || timings[0].isBlank() || timings[1].isBlank()) {
                    throw new EkudIllegalArgException("Description/start/end shouldn't be empty :(");
                }
                LocalDateTime fromDateTime = this.parseDateTime(timings[0]);
                LocalDateTime toDateTime = this.parseDateTime(timings[1]);
                return taskList.addEvent(description, fromDateTime, toDateTime);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new EkudIllegalArgException(String.format(
                        "Event formatted wrongly\n"
                        + "-> Ensure 'event <description> /from <%s> /to <%s>' is followed\n"
                        + "-> For example: event company dinner /from 20 Oct 1730 /to 20 Oct 2215",
                        INPUT_DATETIME_FORMAT,
                        INPUT_DATETIME_FORMAT));
            }
        case DELETETASK:
            int deleteTaskNum = this.parseTaskNum(userArgs);
            return taskList.deleteTask(deleteTaskNum - 1);
        case FIND:
            String keyword = this.parseKeyword(userArgs);
            return taskList.findTasks(keyword);
        case CLEAR:
            return taskList.clear();
        case UNDOCLEAR:
            return taskList.undoClear();
        default:
            throw new EkudIllegalArgException("Error parsing arguments :(");
        }
    }

    /**
     * Helper function to check for a valid task number.
     * @param userArgs Number String supplied by the user.
     * @return An integer index number.
     * @throws EkudIllegalArgException
     */
    private int parseTaskNum(String userArgs) throws EkudIllegalArgException {
        try {
            int index = Integer.valueOf(userArgs);
            if (index <= 0) {
                throw new EkudIllegalArgException("Index number cannot be 0 or negative :o");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new EkudIllegalArgException("Please input a valid index number :o");
        }
    }

    /**
     * Helper function to check for a valid keyword.
     * @param userArgs
     * @return
     * @throws EkudIllegalArgException
     */
    private String parseKeyword(String userArgs) throws EkudIllegalArgException {
        String[] keyword = userArgs.split(" ");
        if (keyword.length == 0 || keyword[0].isBlank()) {
            throw new EkudIllegalArgException("Keyword shouldn't be empty :(");
        }
        if (keyword.length > 1) {
            throw new EkudIllegalArgException("Please input a valid keyword (multiple words "
                    + "are not accepted) :(");
        }
        return keyword[0];
    }

    /**
     * Parses the user's input date and time into a LocalDateTime object.
     * @param inputDateTime User's input dateTime in the format dd-MM HHmm.
     * @return LocalDateTime
     */
    public LocalDateTime parseDateTime(String inputDateTime) {
        DateTimeFormatter parseFormatter = new DateTimeFormatterBuilder()
                .parseDefaulting(ChronoField.YEAR, 2023)
                .appendPattern(INPUT_DATETIME_FORMAT)
                .toFormatter(Locale.ENGLISH);
        return LocalDateTime.parse(inputDateTime, parseFormatter);
    }

    /**
     * Parses a date and time from the saved tasks file into a LocalDateTime object.
     * @param savedDateTime Saved dateTime in the format dd MMM yyyy h:mm a.
     * @return LocalDateTime
     */
    public LocalDateTime parseSavedDateTime(String savedDateTime) {
        return LocalDateTime.parse(savedDateTime, DateTimeFormatter.ofPattern(SAVED_DATETIME_FORMAT));
    }
}
