package utility;

import tasks.Task;
import tasks.TaskList;

public class PrintUtility {
  private final static String delimiter = "-".repeat(80);
  private final static int indentation = 4;

  public static void printText(String... lines) {
    String frontPadding = " ".repeat(indentation);
    System.out.printf("%s%s\n", frontPadding, delimiter);
    for (String line : lines) {
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
