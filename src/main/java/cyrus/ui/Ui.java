package cyrus.ui;

import cyrus.tasks.Task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility to handle CLI text printing to ensure uniform output.
 */
public class Ui {
    private final static String DELIMITER = "-".repeat(80);
    private final static int INDENTATION = 4;

    /**
     * Print multiple lines of text with delimiters above and below.
     *
     * <p>If a line contains {@code \n}, then it splits up the text by {@code \n}.</p>
     *
     * @param lines list of lines to display
     */
    public static void printText(String... lines) {
        String frontPadding = " ".repeat(INDENTATION);
        // If there are newlines in text, we want to convert those to separate lines
        // This ensures that our indentation is applied to every new line
        List<String> text =
                Arrays.stream(lines)
                        .flatMap((line) -> Stream.of(line.split("\n")))
                        .collect(Collectors.toList());
        System.out.printf("%s%s\n", frontPadding, DELIMITER);
        for (String line : text) {
            System.out.printf("%s%s\n", frontPadding, line);
        }
        System.out.printf("%s%s\n", frontPadding, DELIMITER);
    }

    /**
     * Helper method to print that a task is added along with task details.
     *
     * @param task         added task
     * @param taskListSize size of the current task list after addition
     */
    public static void printAddTask(Task task, int taskListSize) {
        printText(
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list.", taskListSize)
        );
    }
}
