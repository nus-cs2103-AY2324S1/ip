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

  private static int extractIndex(String text, int pos) {
    String[] parts = text.split(" ");
    if (parts.length <= 1) return -1;
    try {
      return Integer.parseInt(parts[pos]);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private static void printAddTask(Task task) {
    printText(
        "Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d tasks in the list.", tasks.size())
    );
  }

  private static void addToDo(String line) {
    String content = line.substring("todo ".length());
    ToDo todo = new ToDo(content);
    tasks.add(todo);
    printAddTask(todo);
  }

  private static void addDeadline(String line) {
    String content = line.substring("deadline ".length());
    String[] parts = content.split(" /by ");
    if (parts.length != 2) {
      printText("Invalid deadline format.");
      return;
    }
    Deadline deadline = new Deadline(parts[0], parts[1]);
    tasks.add(deadline);
    printAddTask(deadline);
  }

  private static void addEvent(String line) {
    String content = line.substring("event ".length());
    String[] parts = content.split(" /from ");
    if (parts.length != 2) {
      printText("Invalid event format.");
      return;
    }
    String name = parts[0];
    String[] rest = parts[1].split(" /to ");
    if (rest.length != 2) {
      printText("Invalid event format.");
      return;
    }
    Task event = new Event(name, rest[0], rest[1]);
    tasks.add(event);
    printAddTask(event);
  }

  private static int updateTaskStatus(String line, boolean status) {
    int i = extractIndex(line, 1);
    if ((i - 1) < 0 || (i - 1) > tasks.size()) {
      printText("Invalid index provided.");
    }
    tasks.get(i - 1).setDone(status);
    return i;
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

      String[] parts = input.split(" ");
      String command = parts[0];
      if (command.equals("bye")) break;

      switch (command) {
        case "todo":
          addToDo(input);
          break;
        case "deadline":
          addDeadline(input);
          break;
        case "event":
          addEvent(input);
          break;
        case "mark":
          int idx = updateTaskStatus(input, true);
          printText("Nice! I've marked this task as done:", tasks.get(idx - 1).toString());
          break;
        case "unmark":
          idx = updateTaskStatus(input, false);
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
