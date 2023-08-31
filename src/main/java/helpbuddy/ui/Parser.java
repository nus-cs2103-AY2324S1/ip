package helpbuddy.ui;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.*;
import helpbuddy.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseCommand(String userCommand) throws HelpBuddyException, DateTimeParseException {
        String[] userInput = userCommand.split(" ", 2);
        String command = userInput[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(Integer.parseInt(userInput[1]));
        case ("unmark"):
            return new UnmarkCommand(Integer.parseInt(userInput[1]));
        case ("todo"):
            if (userInput.length == 1) {
                return new AddCommand(new ToDo(""));
            }
            return new AddCommand(new ToDo(userInput[1]));
        case ("deadline"): {
            if (userInput.length == 1) {
                return new AddCommand(new Deadline("", null));
            }
            String[] taskDetails = userInput[1].split("/by", 2);
            String taskName = taskDetails[0].trim();
            if (taskDetails.length == 1) {
                return new AddCommand(new Deadline(taskName, null));
            }
            String deadlineTime = taskDetails[1].trim();
            if (deadlineTime.isBlank()) {
                return new AddCommand(new Deadline(taskName, null));
            }
            return new AddCommand(new Deadline(taskName, LocalDateTime.parse(deadlineTime, formatter)));
        }
        case ("event"): {
            if (userInput.length == 1) {
                return new AddCommand(new Event("", null, null));
            }
            String[] taskDetails = userInput[1].split("/from", 2);
            String taskName = taskDetails[0].trim();
            if (taskDetails.length == 1) {
                return new AddCommand(new Event(taskName, null, null));
            }
            String[] taskDateDetails = taskDetails[1].split("/to", 2);
            String startTime = taskDateDetails[0].trim();
            if (startTime.isBlank()) {
                return new AddCommand(new Event(taskName, null, null));
            }
            if (taskDateDetails.length == 1) {
                return new AddCommand(new Event(taskName,
                        LocalDateTime.parse(startTime, formatter),
                        null
                ));
            }
            String endTime = taskDateDetails[1].trim();
            if (startTime.isBlank()) {
                return new AddCommand(new Event(
                        taskName,
                        LocalDateTime.parse(startTime, formatter),
                        null
                ));
            }
            return new AddCommand(new Event(
                    taskName,
                    LocalDateTime.parse(startTime, formatter),
                    LocalDateTime.parse(endTime, formatter)
            ));
        }
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(userInput[1]));
        case ("bye"):
            return new ExitCommand();
        default:
            throw new HelpBuddyException("I'm sorry, but I don't know what that means.\n");
        }
    }
}
