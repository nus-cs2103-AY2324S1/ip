package utility;

import tasks.Task;
import tasks.TaskList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintUtility {
  private final static String delimiter = "-".repeat(80);
  private final static int indentation = 4;

  public static void printText(String... lines) {
    String frontPadding = " ".repeat(indentation);
    // If there are newlines in text, we want to convert those to separate lines
    // This ensures that our indentation is applied to every new line
    List<String> text =
        Arrays.stream(lines)
            .flatMap((line) -> Stream.of(line.split("\n")))
            .collect(Collectors.toList());
    System.out.printf("%s%s\n", frontPadding, delimiter);
    for (String line : text) {
      System.out.printf("%s%s\n", frontPadding, line);
    }
    System.out.printf("%s%s\n", frontPadding, delimiter);
  }

  public static void printAddTask(Task task) {
    TaskList tasks = TaskList.getInstance();
    printText(
        "Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d tasks in the list.", tasks.size())
    );
  }
}
