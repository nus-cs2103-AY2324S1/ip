package duke.service;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * Handles the display of information to the user with standardized formatting.
 * <p>
 * The OutputService ensures consistent display formats, particularly with the use
 * of indentation and dividers for enhanced readability.
 * </p>
 */
public class OutputService {
    private static final int indentLength = 4;
    private final String divider = appendNewLine(
        indentLeft(String.format("%40s", "").replace(" ", "-")));

    /**
     * Echos the provided input string to the user without a prefix.
     *
     * @param input The string to be displayed.
     */
    public String echo(String input) {
        return echo(input, "");
    }

    /**
     * Echos the provided input string to the user with the specified prefix.
     *
     * @param input  The string to be displayed.
     * @param prefix A prefix to be added before the input string.
     */
    public String echo(String input, String prefix) {
        return echo(List.of(prefix + input));
    }

    /**
     * Echos a list of strings to the user with standardized formatting.
     *
     * @param inputs The list of strings to be displayed.
     */
    public String echo(List<String> inputs) {
        StringBuilder sb = new StringBuilder();
        sb.append(divider);
        inputs.stream().map(this::indentLeft)
            .map(this::appendNewLine)
            .forEach(sb::append);
        sb.append(divider);
        return sb.toString();
    }

    /**
     * Adds a standardized indentation to the beginning of each line of the given string.
     *
     * @param input The string that requires indentation.
     * @return The input string with the standardized indentation applied.
     */
    public String indentLeft(String input) {
        String indent = String.format("%" + indentLength + "s", "");
        String[] lines = input.split(System.lineSeparator()); // handle Unix and Windows new lines.
        for (int i = 0; i < lines.length; i++) {
            lines[i] = indent + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    /**
     * Appends to the front of each task in a list of tasks to the user with task numbers.
     *
     * @param taskList The list of tasks to be formatted.
     */
    public List<String> formatTaskList(List<Task> taskList) {
        List<String> tasksWithNumber = new ArrayList<>();
        for (int i = 0; i < taskList.size(); ++i) {
            String taskNumber = String.format("%s. ", i + 1);
            tasksWithNumber.add(taskNumber + taskList.get(i));
        }
        return tasksWithNumber;
    }

    private String appendNewLine(String line) {
        return String.format("%s%n", line);
    }
}
