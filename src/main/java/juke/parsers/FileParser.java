package juke.parsers;

import juke.core.JukeObject;
import juke.exceptions.JukeParseException;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTask;
import juke.tasks.JukeTodo;

/**
 * Parses the datafile back into JukeTask objects.
 * <p>
 * This parser may not be instantiated. All methods are exposed via
 * static methods.
 */
public abstract class FileParser extends JukeObject {
    /** Regex String used to parse the datafile lines. */
    private static final String REGEX = "\\|";

    /**
     * Parses a single task into a {@code JukeTask} object.
     * @param task Single task to parse
     * @return {@code JukeTask} object
     * @throws JukeParseException if there are errors with parsing the datafile
     */
    public static JukeTask parseTask(String task) {
        String[] data = task.split(REGEX);

        if (data.length == 0) {
            throw new JukeParseException("Oh no! Data \"" + task + "\" cannot be parsed!");
        }

        switch (data[0]) {
        case "T":
            return new JukeTodo(data[2], data[1].equals("T"));
        case "D":
            return new JukeDeadline(data[2], DateTimeParser.fromParsedString(data[3]), data[1].equals("T"));
        case "E":
            return new JukeEvent(data[2], DateTimeParser.fromParsedString(data[3]),
                                 DateTimeParser.fromParsedString(data[4]), data[1].equals("T"));
        default:
            throw new JukeParseException("Oh no! Data \"" + task + "\" cannot be parsed!");
        }
    }
}
