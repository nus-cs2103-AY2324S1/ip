package main.java.juke.parsers;

import main.java.juke.exceptions.JukeParseException;
import main.java.juke.core.JukeObject;
import main.java.juke.tasks.*;

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
     * @throws {@code JukeParseException} if there are errors with parsing the datafile
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
