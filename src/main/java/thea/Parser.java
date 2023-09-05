package thea;

import java.util.ArrayList;
import java.util.Collections;

import java.time.LocalDateTime;

public class Parser {

    public static Command parse(String fullCommand) throws EmptyDescriptionException, WrongCommandException, WrongDateTimeFormatException {
        String[] commandWords = fullCommand.split(" ", 2);
        ArrayList<String> commandWordsArray = new ArrayList<>();
        Collections.addAll(commandWordsArray, commandWords);
        String command = commandWords[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintListCommand();
        case "mark": {
            int index = Integer.parseInt(commandWords[1]) - 1;
            return new MarkCommand(index);
        }
        case "unmark": {
            int index = Integer.parseInt(commandWords[1]) - 1;
            return new UnmarkCommand(index);
        }
        case "delete": {
            int index = Integer.parseInt(commandWords[1]) - 1;
            return new DeleteCommand(index);
        }
        case "todo":
            if (commandWordsArray.size() != 1) {
                ToDo todo = new ToDo(commandWords[1]);
                return new AddCommand("T", todo);
            } else {
                throw new EmptyDescriptionException("The description of a todo cannot be empty! '^'");
            }
        case "deadline":
            if (commandWordsArray.size() != 1) {
                String relevantData = commandWords[1];
                String[] nameAndTime = relevantData.split(" /by ");
                try {
                    String[] dateYearMonthDay = nameAndTime[1].split(" ")[0].split("-");
                    String[] timeHourMinute = nameAndTime[1].split(" ")[1].split(":");
                    LocalDateTime dueDate = LocalDateTime.of(Integer.parseInt(dateYearMonthDay[0]),
                            Integer.parseInt(dateYearMonthDay[1]),
                            Integer.parseInt(dateYearMonthDay[2]),
                            Integer.parseInt(timeHourMinute[0]),
                            Integer.parseInt(timeHourMinute[1]));
                } catch (Exception e) {
                    throw new WrongDateTimeFormatException("I cannot understand your due date '^' " +
                            "Please write your due date in format yyyy-MM-dd HH:mm");
                }
                Deadline deadline = new Deadline(nameAndTime[0], nameAndTime[1]);
                return new AddCommand("D", deadline);
            } else {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty! '^'");
            }
        case "event":
            if (commandWordsArray.size() != 1) {
                String relevantData = commandWords[1];
                String[] nameAndTime = relevantData.split(" /from | /to ");
                try {
                    String[] dateYearMonthDayFrom = nameAndTime[1].split(" ")[0].split("-");
                    String[] timeHourMinuteFrom = nameAndTime[1].split(" ")[1].split(":");
                    String[] dateYearMonthDayTo = nameAndTime[2].split(" ")[0].split("-");
                    String[] timeHourMinuteTo = nameAndTime[2].split(" ")[1].split(":");
                    LocalDateTime from = LocalDateTime.of(
                            Integer.parseInt(dateYearMonthDayFrom[0]),
                            Integer.parseInt(dateYearMonthDayFrom[1]),
                            Integer.parseInt(dateYearMonthDayFrom[2]),
                            Integer.parseInt(timeHourMinuteFrom[0]),
                            Integer.parseInt(timeHourMinuteFrom[1]));
                    LocalDateTime to = LocalDateTime.of(
                            Integer.parseInt(dateYearMonthDayTo[0]),
                            Integer.parseInt(dateYearMonthDayTo[1]),
                            Integer.parseInt(dateYearMonthDayTo[2]),
                            Integer.parseInt(timeHourMinuteTo[0]),
                            Integer.parseInt(timeHourMinuteTo[1]));
                } catch (Exception e) {
                    throw new WrongDateTimeFormatException("I cannot understand your date and time '^' " +
                            "Please write your event date and time in format yyyy-MM-dd HH:mm");
                }
                Event event = new Event(nameAndTime[0], nameAndTime[1], nameAndTime[2]);
                return new AddCommand("E", event);
            } else {
                throw new EmptyDescriptionException("The description of an event cannot be empty! '^'");
            }
        default:
            throw new WrongCommandException("Sorry, I don't understand what that means.. '^'");
        }
    }
}
