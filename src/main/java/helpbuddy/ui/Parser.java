package helpbuddy.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import helpbuddy.command.AddCommand;
import helpbuddy.command.Command;
import helpbuddy.command.DeleteCommand;
import helpbuddy.command.ExitCommand;
import helpbuddy.command.FindCommand;
import helpbuddy.command.ListCommand;
import helpbuddy.command.MarkCommand;
import helpbuddy.command.UnmarkCommand;
import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.Deadline;
import helpbuddy.task.Event;
import helpbuddy.task.ToDo;

/**
 * A Parser class interprets the user input and produces a corresponding Command.
 */
public class Parser {

    /**
     * Returns an AddCommand for ToDo task.
     * @param userInput A String array containing user's input to HelpBuddy.
     * @return an AddCommand for ToDo task.
     * @throws HelpBuddyException if user input for ToDo task is invalid.
     */
    private static Command addToDoCommand(String[] userInput) throws HelpBuddyException {
        if (userInput.length == 1) {
            return new AddCommand(new ToDo(""));
        }
        String taskDescription = userInput[1];
        return new AddCommand(new ToDo(taskDescription));
    }

    /**
     * Returns an AddCommand for Deadline task.
     * @param userInput A String array containing user's input to HelpBuddy.
     * @return an AddCommand for Deadline task.
     * @throws HelpBuddyException if user input for Deadline task is invalid.
     */
    private static Command addDeadlineCommand(String[] userInput) throws HelpBuddyException {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        return new AddCommand(new Deadline(taskName, LocalDateTime.parse(deadlineTime, formatter)));
    }

    /**
     * Returns an AddCommand for Event task.
     * @param userInput A String array containing user's input to HelpBuddy.
     * @return an AddCommand for Event task.
     * @throws HelpBuddyException if user input for Event task is invalid.
     */
    private static Command addEventCommand(String[] userInput) throws HelpBuddyException {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
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

    /**
     * Returns a FindCommand.
     * @param userInput A String array containing user's input to HelpBuddy.
     * @return a FindCommand.
     * @throws HelpBuddyException if user did not input anything to find.
     */
    private static Command findTaskCommand(String[] userInput) throws HelpBuddyException {
        if (userInput.length == 1) {
            return new FindCommand("");
        }
        String taskPrefix = userInput[1].trim();
        return new FindCommand(taskPrefix);
    }

    /**
     * Interprets the userCommand and produces a corresponding Command.
     * @param userCommand the String that user keys into HelpBuddy.
     * @return the Command to be executed by HelpBuddy.
     * @throws HelpBuddyException if userCommand is invalid.
     * @throws DateTimeParseException if the time keyed in for Deadline and Event Task is
     *     not in the format of dd/MM/yy HH:mm.
     */
    public static Command parseCommand(String userCommand) throws HelpBuddyException, DateTimeParseException {
        String[] userInput = userCommand.split(" ", 2);
        String command = userInput[0];

        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("mark"):
            int taskIndex = Integer.parseInt(userInput[1]);
            return new MarkCommand(taskIndex);
        case ("unmark"):
            int taskNumber = Integer.parseInt(userInput[1]);
            return new UnmarkCommand(taskNumber);
        case ("todo"):
            return addToDoCommand(userInput);
        case ("deadline"): {
            return addDeadlineCommand(userInput);
        }
        case ("event"): {
            return addEventCommand(userInput);
        }
        case("find"):
            return findTaskCommand(userInput);
        case ("delete"):
            int taskRank = Integer.parseInt(userInput[1]);
            return new DeleteCommand(taskRank);
        case ("bye"):
            return new ExitCommand();
        default:
            throw new HelpBuddyException("I'm sorry, but I don't know what that means.\n");
        }
    }
}
