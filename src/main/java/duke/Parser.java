package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Handles the parsing of user input commands and creates corresponding Command objects.
 * This class is responsible for interpreting user input and generating appropriate
 * Command objects based on the input.
 */
public class Parser {

    /**
     * Parses the given input and returns the corresponding Command object.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the parsed user input.
     * @throws DukeException If the input cannot be parsed or contains errors.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.contains("find")) {
            String subInput;
            try {
                subInput = input.substring(5);
                if (subInput.trim().equals("")) {
                    throw new DukeException("The keyword to find cannot be empty.");
                }
            } catch (Exception e) {
                throw new DukeException("The keyword to find cannot be empty.");
            }
            return new FindCommand(subInput);
        } else if (input.contains("todo")) {
            String subInput;
            try {
                subInput = input.substring(5);
                if (subInput.trim().equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } catch (Exception e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            ToDo t = new ToDo(subInput);
            return new AddCommand(t);
        } else if (input.contains("deadline")) {
            String[] split = input.split(" /by ", 2);
            if (split.length == 1) {
                throw new DukeException("Deadlines must have a /by.");
            } else if (split[1].trim().equals("")) {
                throw new DukeException("/by cannot be empty.");
            }
            String description = split[0].substring(9);
            if (description.trim().equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String str = split[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(str, formatter);
            } catch (Exception e) {
                throw new DukeException("DateTime should be in yyyy-MM-dd HH:mm.");
            }

            Deadline t = new Deadline(description, dateTime);
            return new AddCommand(t);
        } else if (input.contains("event")) {
            String[] split = input.split(" /from ", 2);
            if (split.length == 1) {
                throw new DukeException("Events must have a /from and /to.");
            }
            String description;
            try {
                description = split[0].substring(6);
                if (description.trim().equals("")) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
            } catch (Exception e) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            String[] duration = split[1].split(" /to ", 2);
            if (duration.length == 1) {
                throw new DukeException("Events must have a /from and /to.");
            } else if (duration[0].trim().equals("") || duration[1].trim().equals("")) {
                throw new DukeException("/from and /to cannot be empty.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime dateTimeStart;
            LocalDateTime dateTimeEnd;
            try {
                dateTimeStart = LocalDateTime.parse(duration[0], formatter);
                dateTimeEnd = LocalDateTime.parse(duration[1], formatter);
            } catch (Exception e) {
                throw new DukeException("DateTime should be in yyyy-MM-dd HH:mm.");
            }

            Event t = new Event(description, dateTimeStart, dateTimeEnd);
            return new AddCommand(t);
        } else if (input.contains("unmark")) {
            if (input.length() < 7) {
                throw new DukeException("duke.task.Task number to be unmarked cannot be empty.");
            }
            String subInput = input.substring(7);
            int targetIndex;
            try {
                targetIndex = Integer.parseInt(subInput);
            } catch (NumberFormatException e) {
                throw new DukeException("duke.task.Task to be unmarked must be a number.");
            }
            return new UnmarkCommand(targetIndex);

        } else if (input.contains("mark")) {
            if (input.length() < 5) {
                throw new DukeException("duke.task.Task number to be marked cannot be empty.");
            }
            String subInput = input.substring(5);
            int targetIndex;
            try {
                targetIndex = Integer.parseInt(subInput);
            } catch (NumberFormatException e) {
                throw new DukeException("duke.task.Task to be marked must be a number.");
            }
            return new MarkCommand(targetIndex);
        } else if (input.contains("delete")) {
            if (input.length() < 7) {
                throw new DukeException("duke.task.Task number to be deleted cannot be empty.");
            }
            String subInput = input.substring(7);
            int targetIndex;
            try {
                targetIndex = Integer.parseInt(subInput);
            } catch (NumberFormatException e) {
                throw new DukeException("duke.task.Task to be deleted must be a number.");
            }
            return new DeleteCommand(targetIndex);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
