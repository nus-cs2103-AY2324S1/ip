package duke.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.task.Task.TaskType;

/**
 * Class to parse user input.
 */
public class Parser {

    /* DateTimeFormatter for parsing date and time input */
    private static DateTimeFormatter dateTimeParser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");

    /**
     * Parses a date and time input string into a TemporalAccessor object.
     * The input string must be in the format "DD-MM-YYYY [HHMM]".
     * The TemporalAccessor object returned is either a LocalDate or LocalDateTime object.
     *
     * @param dateTimeString Date and time input string.
     * @return TemporalAccessor object representing the date and time input.
     * @throws DukeException If the input string is not in the correct format.
     */
    public static TemporalAccessor parseDateTimeInput(String dateTimeString) throws DukeException {
        try {
            return dateTimeParser.parseBest(dateTimeString,
                    LocalDateTime::from,
                    LocalDate::from);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
        }
    }

    /**
     * Parses a user input string into a Command object.
     *
     * @param fullCommand User input string.
     * @return Command object representing the user input.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.trim().split(" ", 2);
        String commandString = splitCommand[0];
        String parameters = "";

        if (splitCommand.length == 2) {
            parameters = splitCommand[1];
        }

        Map<String, String> parameterMap = parseParameters(parameters);

        switch (commandString) {
        case "bye":
            return new ExitCommand(parameterMap);
        case "list":
            return new ListCommand(parameterMap);
        case "mark":
            return new MarkCommand(parameterMap);
        case "unmark":
            return new UnmarkCommand(parameterMap);
        case "todo":
            return new AddCommand(parameterMap, TaskType.TODO);
        case "deadline":
            return new AddCommand(parameterMap, TaskType.DEADLINE);
        case "event":
            return new AddCommand(parameterMap, TaskType.EVENT);
        case "delete":
            return new DeleteCommand(parameterMap);
        case "find":
            return new FindCommand(parameterMap);
        case "sort":
            return new SortCommand(parameterMap);
        default:
            throw new DukeException("Please enter a valid command.");
        }
    }

    private static Map<String, String> parseParameters(String parameters) {
        String[] parameterArray = parameters.trim().split("/");
        HashMap<String, String> parameterMap = new HashMap<>();

        if (parameterArray.length == 0) {
            return parameterMap;
        }

        // Handles default parameter provided
        if (!parameterArray[0].trim().equals("")) {
            parameterMap.put("default", parameterArray[0]);
        }

        for (int i = 1; i < parameterArray.length; i++) {
            String[] splitParameter = parameterArray[i].trim().split(" ", 2);

            // No data provided for parameter
            if (splitParameter.length == 1) {
                continue;
            }

            parameterMap.put(splitParameter[0].trim(), splitParameter[1].trim());
        }

        return parameterMap;
    }

    /**
     * Parses a task data string into an AddCommand object.
     * Primarily used for parsing task data from storage.
     *
     * @param taskDataString Task data string.
     * @return AddCommand object representing the task data.
     */
    public static AddCommand parseTaskDataString(String taskDataString) throws DukeException {
        String[] parameterArray = taskDataString.trim().split(" \\| ");

        if (parameterArray.length < 3) {
            return null;
        }

        String taskLetter = parameterArray[0];
        boolean isTaskCompleted = parameterArray[1].equals("1");
        String description = parameterArray[2];
        String firstDateTime = null;
        String secondDateTime = null;

        // If there is a date and time stored
        if (parameterArray.length > 3) {
            firstDateTime = parameterArray[3];
        }

        // If there is a second date and time stored
        if (parameterArray.length > 4) {
            secondDateTime = parameterArray[4];
        }

        HashMap<String, String> parameterMap = new HashMap<>();

        parameterMap.put("default", description);

        if (firstDateTime != null) {
            parameterMap.put("by", firstDateTime);
            parameterMap.put("from", firstDateTime);
        }

        if (secondDateTime != null) {
            parameterMap.put("to", secondDateTime);
        }

        switch(taskLetter) {
        case "T":
            return new AddCommand(parameterMap, TaskType.TODO, isTaskCompleted, true);
        case "D":
            return new AddCommand(parameterMap, TaskType.DEADLINE, isTaskCompleted, true);
        case "E":
            return new AddCommand(parameterMap, TaskType.EVENT, isTaskCompleted, true);
        default:
            return null;
        }
    }
}
