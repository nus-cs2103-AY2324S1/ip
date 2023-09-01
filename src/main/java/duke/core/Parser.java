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
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Class to parse user input.
 */
public class Parser {
    
    /* DateTimeFormatter for parsing date and time input */
    public static DateTimeFormatter dateTimeParser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");

    /**
     * Parses a date and time input string into a TemporalAccessor object.
     * The input string must be in the format "DD-MM-YYYY [HHMM]".
     * The TemporalAccessor object returned is either a LocalDate or LocalDateTime object.
     * 
     * @param dateTimeString Date and time input string.
     * @return TemporalAccessor object representing the date and time input.
     * @throws DukeException If the input string is not in the correct format.
     */
    public static TemporalAccessor parseDateTimeInput(String dateTimeString) throws DukeException{
        try {
            return dateTimeParser.parseBest(dateTimeString,
                                        LocalDateTime::from,
                                        LocalDate::from);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
        }
    }

    private static Command commandSwitcher(String command) {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "todo":
            return new AddCommand(AddCommand.TaskType.TODO);
        case "deadline":
            return new AddCommand(AddCommand.TaskType.DEADLINE);
        case "event":
            return new AddCommand(AddCommand.TaskType.EVENT);
        case "delete": 
            return new DeleteCommand();
        default:
            return null;
        }
    }

    /**
     * Parses a user input string into a Command object.
     * 
     * @param fullCommand User input string.
     * @return Command object representing the user input.
     */
    public static Command parseCommand(String fullCommand) {
        String[] splitCommand = fullCommand.trim().split(" ", 2);
        Command command = commandSwitcher(splitCommand[0]);

        if (splitCommand.length == 2) {
            command.addParameterMap(parseParameters(splitCommand[1]));
        }
        return command;
    }

    private static Map<String, String> parseParameters(String parameters) {
        String[] parameterArray = parameters.trim().split("/");

        if (parameterArray.length == 0) {
            return new HashMap<>();
        }

        HashMap<String, String> parameterMap = new HashMap<>();

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
    public static AddCommand parseTaskDataString(String taskDataString) {
        String[] parameterArray = taskDataString.trim().split(" \\| ");

        if (parameterArray.length < 3) {
            return null;
        }

        String taskType = parameterArray[0];
        boolean todoCompletion = parameterArray[1].equals("1");
        String description = parameterArray[2];
        String firstDateTime = null;
        String secondDateTime = null;

        if (parameterArray.length > 3) {
            firstDateTime = parameterArray[3];
        }

        if (parameterArray.length > 4) {
            secondDateTime = parameterArray[4];
        }

        AddCommand command = null;

        switch(taskType) {
        case "T":
            command = new AddCommand(AddCommand.TaskType.TODO);
            break;
        case "D":
            command = new AddCommand(AddCommand.TaskType.DEADLINE);
            break;
        case "E":
            command = new AddCommand(AddCommand.TaskType.EVENT);
            break;
        }

        HashMap<String, String> parameterMap = new HashMap<>();

        parameterMap.put("default", description);
        parameterMap.put("silent", "");

        if (todoCompletion) {
            parameterMap.put("completed", "");
        }

        if (firstDateTime != null) {
            parameterMap.put("by", firstDateTime);
            parameterMap.put("from", firstDateTime);
        }

        if (secondDateTime != null) {
            parameterMap.put("to", secondDateTime);
        }

        command.addParameterMap(parameterMap);

        return command;
    }
}
