package thea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Parser that translates the user input from String to Command class.
 * Returns the user input as subclasses of Command class.
 */
public class Parser {


    /**
     * Translates the user input as Command class.
     *
     * @param fullCommand user input in String.
     * @return executable Command class.
     * @throws EmptyDescriptionException if user tries to add a new task with no description.
     * @throws WrongCommandException if user write the wrong command (first word input).
     * @throws WrongDateTimeFormatException if the inputted date time fails to follow the expected date time format.
     */
    public static Command parse(String fullCommand) throws EmptyDescriptionException,
            WrongCommandException, WrongDateTimeFormatException {
        String[] splitCommand = fullCommand.split(" ", 2);
        ArrayList<String> commandWords = new ArrayList<>(List.of(splitCommand));
        String command = commandWords.get(0);
        int index;
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintListCommand();
        case "mark":
            index = Integer.parseInt(commandWords.get(1)) - 1;
            return new MarkCommand(index);
        case "unmark":
            index = Integer.parseInt(commandWords.get(1)) - 1;
            return new UnmarkCommand(index);
        case "delete":
            index = Integer.parseInt(commandWords.get(1)) - 1;
            return new DeleteCommand(index);
        case "find":
            return new FindCommand(commandWords.get(1));
        case "todo":
            if (commandWords.size() == 1) {
                throw new EmptyDescriptionException("The description of a todo cannot be empty! '^'");
            }
            ToDo todo = new ToDo(commandWords.get(1));
            return new AddCommand("T", todo);
        case "deadline":
            Deadline deadline = createDeadline(commandWords);
            return new AddCommand("D", deadline);
        case "event":
            Event event = createEvent(commandWords);
            return new AddCommand("E", event);
        case "load":
            return new LoadCommand(commandWords.get(1));
        default:
            throw new WrongCommandException("Sorry, I don't understand what that means.. '^'");
        }
    }

    /**
     * Creates an Event class from user command.
     *
     * @param commandWords user input in ArrayList of Strings.
     * @return the created Event task.
     * @throws EmptyDescriptionException if user tries to add a new task with no description.
     * @throws WrongDateTimeFormatException if the inputted date time fails to follow the expected date time format.
     */
    private static Event createEvent(ArrayList<String> commandWords) throws EmptyDescriptionException,
            WrongDateTimeFormatException {
        if (commandWords.size() == 1) {
            throw new EmptyDescriptionException("The description of an event cannot be empty! '^'");
        }
        String eventData = commandWords.get(1);
        String[] nameAndTime = eventData.split(" /from | /to ");
        String eventName = nameAndTime[0];
        String eventStartDate = nameAndTime[1];
        String eventFinishDate = nameAndTime[2];
        checkDateValidity(eventStartDate);
        checkDateValidity(eventFinishDate);
        return new Event(eventName, eventStartDate, eventFinishDate);
    }

    /**
     * Creates a Deadline class from user command.
     *
     * @param commandWords user input in ArrayList of Strings.
     * @return the created Deadline task.
     * @throws EmptyDescriptionException if user tries to add a new task with no description.
     * @throws WrongDateTimeFormatException if the inputted date time fails to follow the expected date time format.
     */
    private static Deadline createDeadline(ArrayList<String> commandWords) throws EmptyDescriptionException,
            WrongDateTimeFormatException {
        if (commandWords.size() == 1) {
            throw new EmptyDescriptionException("The description of a deadline cannot be empty! '^'");
        }
        String deadlineData = commandWords.get(1);
        String[] nameAndTime = deadlineData.split(" /by ");
        String deadlineName = nameAndTime[0];
        String deadlineDate = nameAndTime[1];
        checkDateValidity(deadlineDate);
        return new Deadline(deadlineName, deadlineDate);
    }

    /**
     * Validates a string of date to be of a specified format.
     *
     * @param date the date to be validated
     * @throws WrongDateTimeFormatException when the date is not in expected format.
     */
    private static void checkDateValidity(String date) throws WrongDateTimeFormatException {
        try {
            String[] dateYearMonthDay = date.split(" ")[0].split("-");
            String[] timeHourMinute = date.split(" ")[1].split(":");
            LocalDateTime dueDate = LocalDateTime.of(Integer.parseInt(dateYearMonthDay[0]),
                    Integer.parseInt(dateYearMonthDay[1]),
                    Integer.parseInt(dateYearMonthDay[2]),
                    Integer.parseInt(timeHourMinute[0]),
                    Integer.parseInt(timeHourMinute[1]));
        } catch (Exception e) {
            throw new WrongDateTimeFormatException("I cannot understand your date '^' "
                    + "Please write your date(s) in format yyyy-MM-dd HH:mm");
        }
    }
}
