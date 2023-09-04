package duke.utility;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.MissingTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Parser class encapsulates all parsing actions to
 * break down user's inputs into outputs that can be read by Bobi.
 *
 * @author ruo-x
 */
public class Parser {
    /**
     * Parse input and use the results to return a To-Do object.
     *
     * @param input User's input from the keyboard.
     * @return A To-Do object.
     * @throws EmptyTaskException if user did not input a task name
     */
    public static ToDo parseTodo(String input) throws EmptyTaskException {
        String[] keyword = input.split(" ", 2);
        // checks if user has input a task name
        if (keyword.length == 1 || keyword[1].equals("")) {
            throw new EmptyTaskException();
        }
        // create new To Do task from input
        return new ToDo(false, keyword[1]);
    }

    /**
     * Parse input and use the results to return a Deadline object.
     *
     * @param input User's input from the keyboard.
     * @return A Deadline object.
     * @throws MissingTimeException if user did not input a deadline.
     * @throws EmptyTaskException if user did not input a task name.
     * @throws InvalidDeadlineException if user did not follow the specified format for deadlines.
     */
    public static Deadline parseDeadline(String input) throws MissingTimeException, EmptyTaskException, InvalidDeadlineException {
        try {
            // split input by "/by"
            String[] firstSplit = input.split("/by");

            // checks if user has input a deadline
            if (firstSplit.length == 1 || firstSplit[1].equals(" ")) {
                throw new MissingTimeException();
            }
            // checks if user has input a task name
            String[] secondSplit = firstSplit[0].split(" ", 2);
            if (secondSplit.length == 1 || secondSplit[1].equals("")) {
                throw new EmptyTaskException();
            }

            // fill up a name field with input values
            String name = secondSplit[1];

            String[] thirdSplit = firstSplit[1].split(" ");
            // checks if user has input a valid deadline with date and time
            if (thirdSplit.length == 2 || thirdSplit[2].equals("")) {
                throw new InvalidDeadlineException();
            }

            // create a deadline field with input values
            int hour = Integer.parseInt(thirdSplit[2].substring(0, 2));
            int minute = Integer.parseInt(thirdSplit[2].substring(2));
            LocalDate date = LocalDate.parse(thirdSplit[1]);
            LocalTime time = LocalTime.of(hour, minute);
            LocalDateTime deadline = LocalDateTime.of(date, time);

            // create new Deadline task from variables
            return new Deadline(false, name, deadline);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Parse inputs and use the results to return a new Event object.
     *
     * @param input User's input from the keyboard.
     * @return An Event object.
     * @throws MissingTimeException if user did not input a start/end to the Event.
     * @throws EmptyTaskException if user did not input an event name.
     * @throws InvalidEventException if user did not follow the specified format for start/end date and time.
     */
    public static Event parseEvent(String input) throws MissingTimeException, EmptyTaskException, InvalidEventException {
        try {
            // split input by "/from"
            String[] firstSplit = input.split("/from");
            // checks if user has input a start field
            if (firstSplit.length == 1 || firstSplit[1].equals(" ")) {
                throw new MissingTimeException();
            }

            String[] secondSplit = firstSplit[0].split(" ", 2);
            // checks if user has input a task name
            if (secondSplit.length == 1 || secondSplit[1].equals("")) {
                throw new EmptyTaskException();
            }
            // create a name field with input value
            String name = secondSplit[1];

            String[] thirdSplit = firstSplit[1].split("/to");
            // checks if user has input an end field
            if (thirdSplit.length == 1 || thirdSplit[1].equals(" ") || thirdSplit[0].equals(" ")) {
                throw new MissingTimeException();
            }

            String[] startSplit = thirdSplit[0].split(" ");
            // checks if user has input the correct start field with date and time
            if (startSplit.length == 2 || startSplit[2].equals("")) {
                throw new InvalidEventException();
            }
            // create a start field from input values
            LocalDate startDate = LocalDate.parse(startSplit[1]);
            int startHour = Integer.parseInt(startSplit[2].substring(0, 2));
            int startMinute = Integer.parseInt(startSplit[2].substring(2));
            LocalTime startTime = LocalTime.of(startHour, startMinute);
            LocalDateTime start = LocalDateTime.of(startDate, startTime);

            String[] endSplit = thirdSplit[1].split(" ");
            // checks if user has input the correct end field with date and time
            if (endSplit.length == 2 || endSplit[2].equals("")) {
                throw new InvalidEventException();
            }
            // creates an end field from input values
            LocalDate endDate = LocalDate.parse(endSplit[1]);
            int endHour = Integer.parseInt(endSplit[2].substring(0, 2));
            int endMinute = Integer.parseInt(endSplit[2].substring(2));
            LocalTime endTime = LocalTime.of(endHour, endMinute);
            LocalDateTime end = LocalDateTime.of(endDate, endTime);

            // create new Event task from variables
            return new Event(false, name, start, end);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidEventException();
        }
    }

    /**
     * Parse user's input and return a task number of the task the user wish to perform the action on.
     *
     * @param input User's input from the keyboard.
     * @return Task number of the task to perform action.
     * @throws EmptyTaskException if user did not input a task number.
     */
    public static int parseActions(String input) throws EmptyTaskException {
        String[] keyword = input.split(" ");
        // checks if user has input a task number
        if (keyword.length == 1 || keyword[1].equals("")) {
            throw new EmptyTaskException();
        }
        return Integer.parseInt(keyword[1]);
    }
}
