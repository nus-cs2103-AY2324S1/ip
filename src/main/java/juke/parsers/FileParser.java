package juke.parsers;

import java.time.LocalDateTime;

import juke.core.JukeObject;
import juke.exceptions.parsers.JukeDataFileParseException;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTask;
import juke.tasks.JukeTodo;

/**
 * Parses the datafile back into {@code JukeTask} objects.
 * <p>
 * This parser may not be instantiated. All methods are exposed via
 * static methods.
 */
public abstract class FileParser extends JukeObject {
    /** Regex String used to parse the datafile lines. */
    private static final String REGEX = "\\|";

    /**
     * Parses a single task into a {@code JukeTask} object.
     *
     * @param task Single task to parse
     * @return {@code JukeTask} object
     * @throws JukeDataFileParseException if there are errors with parsing the datafile
     */
    public static JukeTask parseTask(String task) {
        String[] data = task.split(REGEX);

        if (data.length == 0) {
            throw new JukeDataFileParseException("Oh no! Data \"" + task + "\" cannot be parsed!");
        }

        switch (data[0]) {
        case "T":
            if (data.length != 3) {
                throw new JukeDataFileParseException("Oh no! Todo \"" + task + "\" cannot be parsed!");
            }

            return new JukeTodo(data[2], data[1].equals("T"));
        case "D":
            if (data.length != 4) {
                throw new JukeDataFileParseException("Oh no! Deadline \"" + task + "\" cannot be parsed!");
            }

            return new JukeDeadline(data[2], DateTimeParser.fromParsedString(data[3]), data[1].equals("T"));
        case "E":
            if (data.length != 5) {
                throw new JukeDataFileParseException("Oh no! Event \"" + task + "\" cannot be parsed!");
            }

            LocalDateTime start = DateTimeParser.fromParsedString(data[3]);
            LocalDateTime end = DateTimeParser.fromParsedString(data[4]);

            if (start.isAfter(end)) {
                throw new JukeDataFileParseException("Oh no! Event start date cannot be after the end date!");
            }

            return new JukeEvent(data[2], start, end, data[1].equals("T"));
        default:
            throw new JukeDataFileParseException("Oh no! Data \"" + task + "\" cannot be parsed!");
        }
    }
}
