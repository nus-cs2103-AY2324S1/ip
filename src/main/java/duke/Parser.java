package duke;

import duke.command.*;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.exception.UnmatchedArgumentException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

    /**
     * Parses a date and time in string and converts it to a LocalDateTime object.
     *
     * @param dateAndTime The date and time in string to be parsed.
     * @return A LocalDateTime object for the date and time in string format.
     * @throws DateTimeException If invalid date or time is passed in.
     */
    public LocalDateTime checkDateAndTime(String dateAndTime) {

        String[] dateSplit = dateAndTime.split("/");
        if (dateSplit.length < 4) {
            throw new DateTimeException("Please enter the time.");
        }
        int hr = Integer.parseInt(dateSplit[dateSplit.length - 1].substring(0, 2));
        int min = Integer.parseInt(dateSplit[dateSplit.length - 1].substring(2));
        return LocalDateTime.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]), hr, min);
    }

    /**
     * Parses a user input and returns Command instance.
     *
     * @param fullCommand The input of the user, including command and details.
     * @return A `Command` object with the command and details.
     */
    public static Command parse(String fullCommand, int taskSize) throws NoSuchCommandException, InvalidIndexException,
            UnmatchedArgumentException, EmptyDescriptionException {

        String[] splitted = fullCommand.split(" ", 2);
        if (splitted[0].equals("list")) {
            return new ListCommand();
        } else if (splitted[0].equals("bye")) {
            return new ExitCommand();
        }
        if (splitted.length < 2) {
            throw new EmptyDescriptionException("Command");
        }
        Command result;
        String command = splitted[0];
        String detail = splitted[1];

        if (command.equals("mark")) {

            if (detail.equals("")) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(detail);
            if (index > 0 && index <= taskSize) {
                result = new MarkCommand(index);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("delete")) {

            if (detail.equals("")) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(detail);
            if (index > 0 && index <= taskSize) {
                result = new DeleteCommand(index);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("unmark")) {

            if (detail.equals("")) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(detail);
            if (index > 0 && index <= taskSize) {
                result = new UnmarkCommand(index);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("todo")) {
            result = new TodoCommand(detail);
        } else if (command.equals("deadline")) {
            result = new DeadlineCommand(detail);
        } else if (command.equals("event")) {
            result = new EventCommand(detail);
        } else if (command.equals("due")) {
            result = new CheckDuedateCommand(detail);
        } else if (command.equals("find")) {
            result = new FindCommand(detail);

        } else {
            throw new NoSuchCommandException();
        }
        return result;
    }
}
