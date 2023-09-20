package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The class for parsing commands to add tasks.
 */
public class TaskParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param input The date-time string to be parsed.
     * @return The LocalDateTime object parsed from the input string.
     * @throws DukeException If the input string is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            throw new DukeException("DateTime should be in yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Checks for extra parameters in the provided map.
     *
     * @param params      The map of parameters to be checked.
     * @param allowedKeys The list of allowed parameter keys.
     * @throws DukeException If any extra parameters are found.
     */
    public static void checkForExtraParams(Map<String, String> params, String... allowedKeys) throws DukeException {
        ArrayList<String> extraParams = new ArrayList<>();

        for (String key : params.keySet()) {
            boolean isAllowed = false;
            for (String allowedKey : allowedKeys) {
                if (key.equals(allowedKey)) {
                    isAllowed = true;
                    break;
                }
            }
            if (!isAllowed) {
                extraParams.add(key);
            }
        }

        if (!extraParams.isEmpty()) {
            throw new DukeException(String.format("Unknown attributes was found: %s", extraParams));
        }
    }

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
            return new Deadline(description, parseDateTime(split[1]));
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
            return new Event(description, parseDateTime(duration[0]), parseDateTime(duration[1]));
        } else {
            throw new DukeException("I'm not sure what this task means.");
        }
    }
}
