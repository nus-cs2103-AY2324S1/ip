package jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jarvis.commands.ByeCommand;
import jarvis.commands.Command;
import jarvis.commands.DeadlineCommand;
import jarvis.commands.DeleteCommand;
import jarvis.commands.EventCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.TodoCommand;
import jarvis.commands.UnmarkCommand;
import jarvis.exceptions.InvalidCommandException;
import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;

public class Parser {
    
    public static Command parseCommand(String userInput) throws InvalidCommandException {
        String[] userInputSpilt = userInput.split(" ");

        if (userInput.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInputSpilt[0].startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (userInputSpilt[0].equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (userInputSpilt[0].equalsIgnoreCase("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInputSpilt[0].equalsIgnoreCase("todo")) {
            return new TodoCommand(userInput);
        } else if (userInputSpilt[0].equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(userInput);
        } else if (userInputSpilt[0].equalsIgnoreCase("event")) {
            return new EventCommand(userInput);
        } else {
            throw new InvalidCommandException(null);
        }
    }

    public static LocalDateTime parseDateTime(String inputDateTime) throws InvalidDateTimeFormatException {
        List<String> inputFormats = new ArrayList<>();
        inputFormats.add(Ui.DATE_TIME_FORMAT);
        inputFormats.add("dd MMM yyyy HHmm");
        inputFormats.add("yyyy-MM-dd HHmm");
        inputFormats.add("dd/MM/yyyy HHmm");

        LocalDateTime result = null;
        for (String inputFormat : inputFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat);
                LocalDateTime parsedDateTime = LocalDateTime.parse(inputDateTime, formatter);
                result = parsedDateTime;
                break;
            } catch (Exception e) {
                continue;
            }
        }
        
        if (result != null) {
            return result;
        } else {
            throw new InvalidDateTimeFormatException(inputDateTime);
        }
    }

    public static Task parseStringToTask(String line) throws InvalidTaskFormatException {
        try {
            String[] lineSplit = line.split("\\|");
            String taskType = lineSplit[0].trim();
            boolean isCompleted = Integer.parseInt(lineSplit[1].trim()) == 1 ? true : false;
            String taskDetails = lineSplit[2].trim();
    
            switch (taskType) {
                case "T":
                    return new Todo(taskDetails, isCompleted);
                case "D":
                    String deadlineByString = lineSplit[3].trim();
                    LocalDateTime formattedDeadlineBy = parseDateTime(deadlineByString);
                    return new Deadline(taskDetails, formattedDeadlineBy, isCompleted);
                case "E":
                    String fromTime = lineSplit[3].trim();
                    String toTime = lineSplit[4].trim();
                    LocalDateTime formattedFromTime = parseDateTime(fromTime);
                    LocalDateTime formattedToTime = parseDateTime(toTime);
                    return new Event(taskDetails, formattedFromTime, formattedToTime, isCompleted);
                default:
                    throw new InvalidTaskFormatException("Unknown Task Type: " + taskType);
            }
        } catch (Exception e) {
            throw new InvalidTaskFormatException("Invalid task format: " + line);
        }
    }
}
