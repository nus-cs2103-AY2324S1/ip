package duke.service;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the display of information to the user with standardized formatting.
 * <p>
 * The OutputService ensures consistent display formats, particularly with the use
 * of indentation and dividers for enhanced readability.
 * </p>
 */
public class OutputService {
    private static final int indentLength = 4;

    /**
     * Echos the provided input string to the user without a prefix.
     *
     * @param input The string to be displayed.
     */
    public void echo(String input) {
        echo(input, "");
    }

    /**
     * Echos the provided input string to the user with the specified prefix.
     *
     * @param input  The string to be displayed.
     * @param prefix A prefix to be added before the input string.
     */
    public void echo(String input, String prefix) {
        echo(List.of(prefix + input));
    }

    /**
     * Echos a list of strings to the user with standardized formatting.
     *
     * @param inputs The list of strings to be displayed.
     */
    public void echo(List<String> inputs) {
        String divider = indentLeft(String.format("%80s", "").replace(" ", "-"));
        System.out.println(divider);
        inputs.stream().map(this::indentLeft)
                .forEach(System.out::println);
        System.out.println(divider);
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
     * Displays a list of tasks to the user with task numbers.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void printTasks(List<Task> taskList) {
        List<String> tasksWithNumber = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i ++) {
            String taskNumber = String.format("%s. ", i + 1);
            tasksWithNumber.add(taskNumber + taskList.get(i));
        }
        echo(tasksWithNumber);
    }
}
