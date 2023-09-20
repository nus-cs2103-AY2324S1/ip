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
import ekud.tasks.Priority;

/**
 * The Parser class serves as an interface between the user and the chatbot by
 * converting user inputs into commands and arguments that the chatbot understands,
 * before finally executing these commands on the chatbot's TaskList and returning
 * a String response.
 */
public class Parser {
    private static final String INPUT_DATETIME_FORMAT = "d MMM HHmm";
    private static final String SAVED_DATETIME_FORMAT = "dd MMM yyyy h:mm a";
    private static final int CUR_YEAR = 2023;

    /**
     * Splits the raw user input into command and arguments.
     *
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
     * Parses the given inputs and handles any invalid command or illegal arguments,
     * before executing the command and returning a String response.
     *
     * @param userInputs The given user inputs which include the command and its respective args.
     * @return String response for the user.
     * @throws EkudException If invalid command or illegal arguments are given.
     */
    public String parseAndExecute(TaskList taskList, String ... userInputs) throws EkudException {
        assert userInputs.length != 0 : "Should supply 2 userInput arguments for parseAndExecute()";
        String userCommand = userInputs[0];
        String userArgs = userInputs[1];
        Command command = Command.getCommand(userCommand); // Command enum
        if (command == null) {
            throw new EkudInvalidCommandException(String.format("Command '%s' not found :(", userCommand));
        }
        switch (command) {
        case SHOWTASKS:
            return taskList.showTasks();
        case MARKTASKASDONE:
            int doneTaskNum = this.parseTaskNum(userArgs, taskList.getSize());
            return taskList.markTaskAsDone(doneTaskNum - 1);
        case MARKTASKASNOTDONE:
            int notDoneTaskNum = this.parseTaskNum(userArgs, taskList.getSize());
            return taskList.markTaskAsNotDone(notDoneTaskNum - 1);
        case ADDTODO:
            return this.parseAndAddTodo(userArgs, taskList);
        case ADDDEADLINE:
            return this.parseAndAddDeadline(userArgs, taskList);
        case ADDEVENT:
            return this.parseAndAddEvent(userArgs, taskList);
        case DELETETASK:
            int deleteTaskNum = this.parseTaskNum(userArgs, taskList.getSize());
            return taskList.deleteTask(deleteTaskNum - 1);
        case FIND:
            String keyword = this.parseKeyword(userArgs);
            return taskList.findTasks(keyword);
        case CLEAR:
            return taskList.clear();
        case UNDOCLEAR:
            return taskList.undoClear();
        case CHANGEPRIORITY:
            return this.parseAndChangePriority(userArgs, taskList);
        default:
            throw new EkudIllegalArgException("Error parsing arguments :(");
        }
    }

    /**
     * Parses the user's input date and time into a LocalDateTime object.
     *
     * @param inputDateTime User's input dateTime in the format dd-MM HHmm.
     * @return LocalDateTime
     */
    public LocalDateTime parseDateTime(String inputDateTime) {
        DateTimeFormatter parseFormatter = new DateTimeFormatterBuilder()
                .parseDefaulting(ChronoField.YEAR, CUR_YEAR)
                .appendPattern(INPUT_DATETIME_FORMAT)
                .toFormatter(Locale.ENGLISH);
        return LocalDateTime.parse(inputDateTime, parseFormatter);
    }

    /**
     * Parses a date and time from the saved tasks file into a LocalDateTime object.
     *
     * @param savedDateTime Saved dateTime in the format dd MMM yyyy h:mm a.
     * @return LocalDateTime
     */
    public LocalDateTime parseSavedDateTime(String savedDateTime) {
        return LocalDateTime.parse(savedDateTime, DateTimeFormatter.ofPattern(SAVED_DATETIME_FORMAT));
    }

    /**
     * Helper function to check for a valid task number.
     *
     * @param userArgs Number String supplied by the user.
     * @return An integer index number
     * @throws EkudIllegalArgException
     */
    private int parseTaskNum(String userArgs, int size) throws EkudIllegalArgException {
        try {
            int taskNum = Integer.valueOf(userArgs);
            if (taskNum <= 0) {
                throw new EkudIllegalArgException("Task number cannot be 0 or negative :o");
            }
            if (taskNum > size) {
                throw new EkudIllegalArgException("Task index number is out of bounds :/");
            }
            return taskNum;
        } catch (NumberFormatException e) {
            throw new EkudIllegalArgException("Please input a valid task number :o");
        }
    }

    /**
     * Helper function to ensure valid arguments for a to-do task before adding it to taskList.
     *
     * @param userArgs
     * @param taskList
     * @return String response after adding task.
     * @throws EkudIllegalArgException
     */
    private String parseAndAddTodo(String userArgs, TaskList taskList)
            throws EkudIllegalArgException {
        if (userArgs.isBlank()) { // isBlank() checks if string is all whitespace
            throw new EkudIllegalArgException("Description shouldn't be empty :(");
        }
        return taskList.addToDo(userArgs);
    }

    /**
     * Helper function to ensure valid arguments for a deadline task before adding it to taskList.
     *
     * @param userArgs
     * @param taskList
     * @return String response after adding task.
     * @throws EkudIllegalArgException
     */
    private String parseAndAddDeadline(String userArgs, TaskList taskList)
            throws EkudIllegalArgException {
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
    }

    /**
     * Helper function to ensure valid arguments for an event task before adding to taskList.
     *
     * @param userArgs
     * @param taskList
     * @return String response after adding task.
     * @throws EkudIllegalArgException
     */
    private String parseAndAddEvent(String userArgs, TaskList taskList) throws EkudIllegalArgException {
        try {
            String[] eventArgs = userArgs.split(" /from ");
            String[] timings = eventArgs[1].split(" /to ");
            String description = eventArgs[0];
            if (description.isBlank() || timings[0].isBlank() || timings[1].isBlank()) {
                throw new EkudIllegalArgException("Description/start/end shouldn't be empty :(");
            }
            LocalDateTime fromDateTime = this.parseDateTime(timings[0]);
            LocalDateTime toDateTime = this.parseDateTime(timings[1]);
            if (fromDateTime.isAfter(toDateTime) || fromDateTime.isEqual(toDateTime)) {
                throw new EkudIllegalArgException("End date & time should be later than the start :(");
            }
            return taskList.addEvent(description, fromDateTime, toDateTime);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new EkudIllegalArgException(String.format(
                    "Event formatted wrongly\n"
                    + "-> Ensure 'event <description> /from <%s> /to <%s>' is followed\n"
                    + "-> For example: event company dinner /from 20 Oct 1730 /to 20 Oct 2215",
                    INPUT_DATETIME_FORMAT,
                    INPUT_DATETIME_FORMAT));
        }
    }

    /**
     * Helper function to check for a valid keyword.
     *
     * @param userArgs
     * @return keyword
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
     * Helper function to ensure valid arguments before changing a task priority.
     *
     * @param userArgs
     * @param taskList
     * @return String response after changing priority
     * @throws EkudIllegalArgException
     */
    private String parseAndChangePriority(String userArgs, TaskList taskList) throws EkudIllegalArgException {
        try {
            String[] prioArgs = userArgs.split(" ");
            int taskNum = this.parseTaskNum(prioArgs[0], taskList.getSize());
            Priority priority = Priority.getPriority(prioArgs[1]);
            if (priority == null) {
                throw new EkudIllegalArgException(
                        "Priority formatted wrongly\n"
                        + "-> Ensure 'priority <taskNum> <high/medium/low>' is followed\n"
                        + "-> For example: priority 3 high");
            }
            return taskList.changePriority(priority, taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Please input a priority level to change to :[");
        }
    }
}
