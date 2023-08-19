import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
  private final static String delimiter = "-".repeat(60);
  private final static int globalIndentation = 4;

  private static void printText(String text, int indentation) {
    String frontPadding = " ".repeat(indentation);
    System.out.printf("%s%s\n", frontPadding, delimiter);
    String[] lines = text.split("\n");
    for (String line : lines) {
      System.out.printf("%s%s\n", frontPadding, line);
    }
    System.out.printf("%s%s\n", frontPadding, delimiter);
  }

  private static int extractIndex(String text, int pos) {
    String[] parts = text.split(" ");
    if (parts.length <= 1) return -1;
    try {
      return Integer.parseInt(parts[pos]);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private static void addToDo(List<Task> tasks, String line) {
    String content = line.substring("todo ".length());
    ToDo todo = new ToDo(content);
    tasks.add(todo);
    printText(
        String.format(
            "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
            todo,
            tasks.size()
        ),
        globalIndentation);
  }

  public static void main(String[] args) {
    printText("Hello! I'm Cyrus\nWhat can I do for you?", globalIndentation);
    String input;
    Scanner sc = new Scanner(System.in);
    List<Task> tasks = new ArrayList<>();
    while (true) {
      input = sc.nextLine();

      String command = input.split(" ")[0];
      int i;
      switch (command) {
        case "bye":
          printText("Bye. Hope to see you again soon!", globalIndentation);
          return;
        case "todo":
          addToDo(tasks, input);
          break;
        case "mark":
          i = extractIndex(input, 1);
          if ((i - 1) < 0 || (i - 1) > tasks.size()) {
            printText("Invalid index provided.", globalIndentation);
          }
          tasks.get(i - 1).setDone(true);
          printText(
              String.format("Nice! I've marked this task as done:\n%s", tasks.get(i - 1)),
              globalIndentation
          );
          break;
        case "unmark":
          i = extractIndex(input, 1);
          if ((i - 1) < 0 || (i - 1) > tasks.size()) {
            printText("Invalid index provided.", globalIndentation);
          }
          tasks.get(i - 1).setDone(false);
          printText(
              String.format("OK, I've marked this task as not done yet:\n%s", tasks.get(i - 1)),
              globalIndentation
          );
          break;
        case "list":
          String output = IntStream
              .range(0, tasks.size())
              .mapToObj((j) -> String.format("%d. %s", j + 1, tasks.get(j)))
              .collect(Collectors.joining("\n"));
          printText(String.format("Here are the tasks in your list:\n%s", output), globalIndentation);
          break;
        default:
          addToDo(tasks, input);
      }
    }
  }
}
