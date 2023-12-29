package juke.parsers;

import java.time.LocalDateTime;

import juke.commons.DateUtils;
import juke.commons.classes.JukeObject;
import juke.commons.exceptions.parsers.JukeDataFileParseException;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTask;
import juke.tasks.JukeTodo;

/**
 * Contains methods used to parse the datafile back into {@code JukeTask} objects.
 * <p>
 * This parser may not be instantiated. All methods are exposed via
 * static methods.
 */
public abstract class FileParser extends JukeObject {
    //@@author asdfghjkxd-reused
    // Regex strings are reused with major modification from ChatGPT, and is built and tested with
    // https://regex101.com/.
    /**
     * Regex String used to parse the datafile lines. The regex patterns below are
     * adapted from ChatGPT, modified to better meet the requirements of
     * Juke.
     * <p>
     * <a href="https://regex101.com/">This</a> was used to build and test the new regex patterns.
     */
    private static final String FILE_SEPARATOR_REGEX = "\\|";
    //@@author

    /**
     * Parses a single task into a {@code JukeTask} object.
     *
     * @param task Single task to parse
     * @return {@code JukeTask} object
     * @throws JukeDataFileParseException if there are errors with parsing the datafile
     */
    public static JukeTask parseTask(String task) {
        String[] data = task.split(FileParser.FILE_SEPARATOR_REGEX);

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

            return new JukeDeadline(data[2], DateTimeParser.fromDateTimeString(data[3]), data[1].equals("T"));
        case "E":
            if (data.length != 5) {
                throw new JukeDataFileParseException("Oh no! Event \"" + task + "\" cannot be parsed!");
            }

            LocalDateTime start = DateTimeParser.fromDateTimeString(data[3]);
            LocalDateTime end = DateTimeParser.fromDateTimeString(data[4]);

            if (DateUtils.isAfter(start, end)) {
                throw new JukeDataFileParseException("Oh no! Event start date cannot be after the end date!");
            }

            return new JukeEvent(data[2], start, end, data[1].equals("T"));
        default:
            throw new JukeDataFileParseException("Oh no! Data \"" + task + "\" cannot be parsed!");
        }
    }
}
