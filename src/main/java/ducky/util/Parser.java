package ducky.util;

import ducky.command.DuckyInvalidCommandException;
import ducky.command.DuckyInvalidCommandFormatException;
import ducky.task.*;
import ducky.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {}

    public static Command parse(String cmd) throws DuckyInvalidCommandException, DuckyInvalidCommandFormatException {
        String[] parts = cmd.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String argumentString = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int markInputIndex;
            try {
                markInputIndex = Integer.parseInt(argumentString);
            } catch (NumberFormatException e) {
                throw new DuckyInvalidCommandFormatException("Did you enter a valid number?");
            }
            return new ChangeTaskCompletionCommand(markInputIndex, true);
        case "unmark":
            int unmarkInputIndex;
            try {
                unmarkInputIndex = Integer.parseInt(argumentString);
            } catch (NumberFormatException e) {
                throw new DuckyInvalidCommandFormatException("Did you enter a valid number?");
            }
            return new ChangeTaskCompletionCommand(unmarkInputIndex, false);
        case "delete":
            return new DeleteCommand(Integer.parseInt(argumentString));
        case "todo":
            // If description argument is empty, throw exception
            if (argumentString.isEmpty()) {
                throw new DuckyInvalidCommandFormatException(
                        "A description is required for creating a to-do."
                );
            }

            return new AddTaskCommand(TaskType.TODO, argumentString);
        case "deadline":
            String[] deadlineParts = argumentString.split("/by", 2);
            // Check if there are 2 arguments
            if (deadlineParts.length < 2) {
                throw new DuckyInvalidCommandFormatException(
                        "A description and deadline (in yyyy-mm-dd format) " +
                                "is required for creating a deadline."
                );
            }
            // Check both arguments are not empty
            for (int i = 0; i < deadlineParts.length; i++) {
                deadlineParts[i] = deadlineParts[i].trim();
                if (deadlineParts[i].isEmpty()) {
                    throw new DuckyInvalidCommandFormatException(
                            "A description and deadline (in yyyy-mm-dd format) " +
                                    "is required for creating a deadline."
                    );
                }
            }

            return new AddTaskCommand(TaskType.DEADLINE, deadlineParts[0], deadlineParts[1]);
        case "event":
            String[] eventParts = argumentString.split("/from|/to", 3);
            // Check if there are 3 arguments
            if (eventParts.length < 3) {
                throw new DuckyInvalidCommandFormatException(
                        "A description, start time and end time is required for creating an event."
                );
            }
            // Check all 3 arguments are not empty
            for (int i = 0; i < eventParts.length; i++) {
                eventParts[i] = eventParts[i].trim();
                if (eventParts[i].isEmpty()) {
                    throw new DuckyInvalidCommandFormatException(
                            "A description, start time and end time is required for creating an event."
                    );
                }
            }

            return new AddTaskCommand(
                    TaskType.EVENT,
                    eventParts[0],
                    eventParts[1],
                    eventParts[2]
            );
        default:
            throw new DuckyInvalidCommandException();
        }
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
    }

    public static Task parseSavedTask(String line) throws DateTimeParseException, DuckyFileParseException {
        String[] lineParts = line.trim().split(" \\| ");
        if (lineParts.length < 3) {
            throw new DuckyFileParseException();
        }
        boolean taskIsDone = lineParts[1].equals("1");

        Task parsedTask;

        switch (lineParts[0]) {
        case "T":
            parsedTask = new TodoTask(lineParts[2]);
            break;
        case "D":
            LocalDate deadline = Parser.parseDate(lineParts[3]);
            parsedTask = new DeadlineTask(lineParts[2], deadline);
            break;
        case "E":
            parsedTask = new EventTask(lineParts[2], lineParts[3], lineParts[4]);
            break;
        default:
            throw new DuckyFileParseException();
        }

        if (taskIsDone) {
            parsedTask.complete();
        }

        return parsedTask;
    }
}
