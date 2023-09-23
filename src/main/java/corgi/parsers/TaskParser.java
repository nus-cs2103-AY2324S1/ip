package corgi.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import corgi.tasks.Deadline;
import corgi.tasks.Event;
import corgi.tasks.Task;
import corgi.tasks.ToDo;

/**
 * The TaskParser class extends the abstract Parser class and provides
 * the implementation to parse string representations of tasks and
 * convert them into Task objects.
 */
public class TaskParser extends Parser<Task> {
    /**
     * The separator used to split task infos in the input string.
     */
    public static final String SEPARATOR = " | ";

    /**
     * Parses the given string representation of a task and converts it into a Task object.
     *
     * @param s The string representation of the task.
     * @return A Task object representing the parsed task.
     */
    @Override
    public Task parse(String s) throws ParsingException {
        String[] infos = s.split(Pattern.quote(SEPARATOR));

        if (infos.length < 3) {
            throw new InvalidParsingFormatException("Invalid task format!");
        }

        String taskType = infos[0];
        String statusStr = infos[1];
        String desc = infos[2];
        boolean isDone = false;

        if (statusStr.equals("1")) {
            isDone = true;
        } else if (statusStr.equals("0")) {
            isDone = false;
        } else {
            throw new InvalidParsingFormatException("Task status should be 0 or 1!");
        }

        Task task = null;

        switch(taskType) {
        case "T":
            if (infos.length != 3) {
                throw new InvalidParsingFormatException("Wrong format for ToDo task!");
            }
            task = new ToDo(isDone, desc);
            break;
        case "D":
            if (infos.length != 4) {
                throw new InvalidParsingFormatException("Wrong format for Deadline task!");
            }

            LocalDate by = null;

            try {
                by = LocalDate.parse(infos[3], Task.DATE_INPUT_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new InvalidParsingFormatException("Wrong format for date!");
            }

            assert by != null : "LocalDate object cannot be null";

            task = new Deadline(isDone, desc, by);

            break;
        case "E":
            if (infos.length != 5) {
                throw new InvalidParsingFormatException("Wrong format for Event task!");
            }

            LocalDate from = null;
            LocalDate to = null;

            try {
                from = LocalDate.parse(infos[3], Task.DATE_INPUT_FORMATTER);
                to = LocalDate.parse(infos[4], Task.DATE_INPUT_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new InvalidParsingFormatException("Wrong format for date!");
            }

            assert from != null : "LocalDate object cannot be null";
            assert to != null : "LocalDate object cannot be null";

            task = new Event(isDone, desc, from, to);
            break;
        default:
            throw new InvalidParsingTypeException("Invalid task type!");
        }

        assert task != null : "Task object cannot be null.";

        return task;
    }
}
