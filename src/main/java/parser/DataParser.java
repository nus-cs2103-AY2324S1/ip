package parser;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The DataParser is responsible for parsing data from storage
 * files and converting them into Task objects.
 */
public class DataParser {
    /**
     * Parses a line from a storage file and converts it into a corresponding Task object.
     *
     * @param line The line to be parsed.
     * @return A Task object corresponding to the parsed line.
     */
    public static Task parseLineToTask(String line) {
        String typeIndicator = line.substring(0, 3);

        switch (typeIndicator) {
            case "[D]":
                return parseDeadline(line);
            case "[E]":
                return parseEvent(line);
            case "[T]":
                return parseToDo(line);
            default:
                System.out.println("Please check the original file for correctness.");
                return null;
        }
    }

    /**
     * Parses a line representing a deadline task and converts it into a Deadline object.
     *
     * @param line The line representing the deadline task.
     * @return A Deadline object corresponding to the parsed line.
     */
    public static Deadline parseDeadline(String line) {
        int descriptionStart = "deadline".length() + 1; // Length of "deadline" plus the space
        int byIndex = line.indexOf("by:");

        String description = line.substring(descriptionStart, byIndex).trim();
        String date = line.substring(byIndex + "/by".length()).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Deadline(description, date, isMarked);
    }

    /**
     * Parses a line representing an event task and converts it into an Event object.
     *
     * @param line The line representing the event task.
     * @return An Event object corresponding to the parsed line.
     */
    public static Event parseEvent(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        int startFromIndex = line.indexOf("from ") + 6; // Index of the character after "from: "
        int toIndex = line.indexOf("to ", startFromIndex); // Index of the character before "to: "
        int endFromIndex = toIndex + 4; // Index of the character after "to: "

        String description = line.substring(descriptionStart, startFromIndex - 8).trim();
        String start = line.substring(startFromIndex, toIndex).trim();
        String end = line.substring(endFromIndex).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Event(description, start, end, isMarked);
    }

    /**
     * Parses a line representing a todo task and converts it into a ToDo object.
     *
     * @param line The line representing the todo task.
     * @return A ToDo object corresponding to the parsed line.
     */
    public static ToDo parseToDo(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        String description = line.substring(descriptionStart).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new ToDo(description, isMarked);
    }
}
