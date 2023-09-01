package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Optional;

/**
 * Parses Task into string and vice versa.
 * @author Toh Li Yuan (A0255811H)
 */
public class TaskParser {

    /**
     * Formats an array of Task for local storage.
     *
     * @param tasks the array of Task to be stored.
     * @return the formatted string to be stored.
     */
    public static String formatSave(Task[] tasks) {
        StringBuilder ret = new StringBuilder();
        for (Task task : tasks) {
            ret.append(task.toSave());
            ret.append("\n");
        }
        return ret.toString().trim();
    }

    /**
     * Parses a string of saved data into Task.
     *
     * @param saveLine the string of saved data.
     * @return an Optional class containing the parsed Task.
     */
    public static Optional<Task> parseSave(String saveLine) {
        String[] splitArray = saveLine.split(" \\|\\| ");
        Optional<Task> ret = Optional.empty();
        switch (splitArray[0]) {
            case "T": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                ret = Optional.of(new Todo(splitArray[1], status));
                break;
            }
            case "D": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                ret = Optional.of(new Deadline(splitArray[1], status, TimeParser.parseTime(splitArray[3])));
                break;
            }
            case "E": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                String[] timeDescriptor = splitArray[3].split(" to ");
                ret = Optional.of(new Event(splitArray[1], status, TimeParser.parseTime(timeDescriptor[0]), TimeParser.parseTime(timeDescriptor[1])));
                break;
            }
        }
        return ret;
    }

}
