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

public class Parser {
    public static DateTimeFormatter dateTimeParser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");

    public static TemporalAccessor parseDateTimeInput(String dateTimeString) throws DukeException{
        try {
            return dateTimeParser.parseBest(dateTimeString,
                    LocalDateTime::from,
                    LocalDate::from);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
        }
    }

    public static Command parseCommand(String fullCommand) {
        String[] splitCommand = fullCommand.trim().split(" ", 2);
        String parameters = "";

        if (splitCommand.length == 2) {
            parameters = splitCommand[1];
        }

        Map<String, String> parameterMap = parseParameters(parameters);

        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand(parameterMap);
        case "list":
            return new ListCommand(parameterMap);
        case "mark":
            return new MarkCommand(parameterMap);
        case "unmark":
            return new UnmarkCommand(parameterMap);
        case "todo":
            parameterMap.put("todo", "");
            return new AddCommand(parameterMap);
        case "deadline":
            parameterMap.put("deadline", "");
            return new AddCommand(parameterMap);
        case "event":
            parameterMap.put("event", "");
            return new AddCommand(parameterMap);
        case "delete": 
            return new DeleteCommand(parameterMap);
        default:
            return null;
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

        switch(taskType) {
        case "T":
            parameterMap.put("todo", "");
            break;
        case "D":
            parameterMap.put("deadline", "");
            break;
        case "E":
            parameterMap.put("event", "");
            break;
        }

        return new AddCommand(parameterMap);
    }
}
