package cyrus.ui;

import cyrus.tasks.Task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ui {
  private final static String DELIMITER = "-".repeat(80);
  private final static int INDENTATION = 4;

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

  public static void printAddTask(Task task, int taskListSize) {
    printText(
        "Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d cyrus.tasks in the list.", taskListSize)
    );
  }
}
