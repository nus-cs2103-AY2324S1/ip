package duke;

import java.util.ArrayList;


/**
 * Object that deals with making sense of user command.
 */
public class Parser {

    public Parser() {
    }

    // takes in a line of text, split into different parts and covert them to a task

    /**
     * Converts a line of text into different useful parts and convert into a task.
     *
     * @param text The line of user input.
     * @return The task made from user input.
     */
    public static Command parse(String text) {
        ArrayList<String> result = new ArrayList<>();
        String[] parts = text.split(" ", 2);
        // add first action string
        result.add(parts[0]);
        // continue parsing if there are more valid words, such as description, dates
        if (parts.length >= 2) {
            parts = parts[1].split(" /", 2);
            // add description
            result.add(parts[0]);
        }
        if (parts.length >= 2) {
            // if able to reach here, the command must be for deadline or Event
            // attempt to split the string after description
            // but only need to split the /from and /to in case of event
            parts = parts[1].split(" /to ", 2);
            if (parts.length == 2) {
                // it must be for Event
                // Event has /from for first string, need to remove it
                result.add(parts[0].substring(5));
                result.add(parts[1]);
            } else {
                // it must be for deadline which only has /by
                // need to remove the by
                parts = parts[0].split("by ");
                if (parts.length >= 2) {
                    result.add(parts[1]);
                }
            }
        }
        return new Command(result);
    }

    /**
     * Converts the segmented strings form the storage/file into Tasks.
     *
     * @param parts The segmented strings containing task information.
     * @return The task described by the information in parts.
     * @throws DukeException If the parts format is wrong and there is an error parsing them into Tasks.
     */
    public static Task parseTask(String[] parts) throws DukeException {
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        try {
            if (taskType.equals("Event")) {
                return new Event(parts[2], parts[3], parts[4]);
            } else if (taskType.equals("Todo")) {
                return new Todo(parts[2]);
            } else if (taskType.equals("Deadline")) {
                return new Deadline(parts[2], parts[3]);
            } else {
                throw new DukeException("Wrong storing format in storage");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong storing format in storage");
        }
    }
}
