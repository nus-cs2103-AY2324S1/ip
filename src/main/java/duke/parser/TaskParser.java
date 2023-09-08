package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The class for parsing commands to add tasks.
 */
public class TaskParser {

    /**
     * Returns the task after parsing the user's input.
     *
     * @param input The user's input with task to be added.
     * @return The task created with the input.
     * @throws DukeException If the input cannot be parsed or contains errors.
     */
    public static Task parseTask(String input) throws DukeException {
        if (input.contains("todo")) {
            String subInput;
            try {
                subInput = input.substring(5);
                if (subInput.trim().equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } catch (Exception e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new ToDo(subInput);
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

            return new Deadline(description, dateTime);
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
            return new Event(description, dateTimeStart, dateTimeEnd);
        } else {
            throw new DukeException("I'm not sure what this task means.");
        }
    }
}
