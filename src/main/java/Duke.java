import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
  private final static String delimiter = "-".repeat(60);
  private final static int globalIndentation = 4;
  private final static List<Task> tasks = new ArrayList<>();

  private static void printText(String... lines) {
    String frontPadding = " ".repeat(Duke.globalIndentation);
    System.out.printf("%s%s\n", frontPadding, delimiter);
    for (String line : lines) {
      System.out.printf("%s%s\n", frontPadding, line);
    }
    System.out.printf("%s%s\n", frontPadding, delimiter);
  }

  private static void printAddTask(Task task) {
    printText(
        "Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d tasks in the list.", tasks.size())
    );
  }

  private static void addToDo(List<String> parts) {
    if (parts.size() == 1) {
      printText("ToDo is missing a body!");
      return;
    }
    String content = String.join(" ", parts.subList(1, parts.size()));
    ToDo todo = new ToDo(content);
    tasks.add(todo);
    printAddTask(todo);
  }

  private static void addDeadline(List<String> parts) {
    if (parts.size() == 1) {
      printText("Deadline is missing a body!");
      return;
    }

    int byIdx = parts.indexOf("/by");
    if (byIdx == -1) {
      printText("Invalid deadline format: missing /by");
      return;
    }

    String deadlineName = String.join(" ", parts.subList(1, byIdx));
    String deadlineBy = String.join(" ", parts.subList(byIdx + 1, parts.size()));

    Task deadline = new Deadline(deadlineName, deadlineBy);
    tasks.add(deadline);
    printAddTask(deadline);
  }

  private static void addEvent(List<String> parts) {
    if (parts.size() == 1) {
      printText("Event is missing a body!");
      return;
    }

    int fromIdx = parts.indexOf("/from");
    if (fromIdx == -1) {
      printText("Invalid event format: missing /from");
      return;
    }

    int toIdx = parts.indexOf("/to");
    if (toIdx == -1) {
      printText("Invalid event format: missing /to");
      return;
    }

    String eventName = String.join(" ", parts.subList(1, fromIdx));
    String eventFrom = String.join(" ", parts.subList(fromIdx + 1, toIdx));
    String eventTo = String.join(" ", parts.subList(toIdx + 1, parts.size()));

    Task event = new Event(eventName, eventFrom, eventTo);
    tasks.add(event);
    printAddTask(event);
  }

  private static int updateTaskStatus(List<String> parts, boolean status) {
    if (parts.size() == 1) return -1;

    try {
      int i = Integer.parseInt(parts.get(1));
      if ((i - 1) < 0 || (i - 1) > tasks.size()) {
        return -1;
      }
      tasks.get(i - 1).setDone(status);
      return i;
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private static void printTasks() {
    List<String> formatted = IntStream
        .range(0, tasks.size())
        .mapToObj((j) -> String.format("%d. %s", j + 1, tasks.get(j)))
        .collect(Collectors.toList());
    formatted.add(0, "Here are the tasks in your list:");
    String[] output = formatted.toArray(String[]::new);
    printText(output);
  }

  public static void main(String[] args) {
    printText("Hello! I'm Cyrus", "What can I do for you?");
    String input;
    Scanner sc = new Scanner(System.in);
    while (true) {
      input = sc.nextLine();

      // Handle empty inputs
      if (input.trim().equals("")) {
        printText("Enter a command please!");
        continue;
      }

      List<String> parts = List.of(input.split(" "));
      String command = parts.get(0);
      if (command.equals("bye")) break;

      switch (command) {
        case "todo":
          addToDo(parts);
          break;
        case "deadline":
          addDeadline(parts);
          break;
        case "event":
          addEvent(parts);
          break;
        case "mark":
          int idx = updateTaskStatus(parts, true);
          if (idx == -1) {
            printText("Invalid index given");
          }
          printText("Nice! I've marked this task as done:", tasks.get(idx - 1).toString());
          break;
        case "unmark":
          idx = updateTaskStatus(parts, false);
          if (idx == -1) {
            printText("Invalid index given");
          }
          printText("OK, I've marked this task as not done yet:", tasks.get(idx - 1).toString());
          break;
        case "list":
          printTasks();
          break;
        default:
          printText("I'm sorry, I don't know what that means :(");
      }
    }

    printText("Bye. Hope to see you again soon!");
  }
}
