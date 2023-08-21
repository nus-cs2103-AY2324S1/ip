import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Duke {

  static final String botName = "GOAT";
  static final Scanner in = new Scanner(System.in);
  static final List<Task> tasks = new ArrayList<>();

  public static void main(String[] args) {
    String logo =
        "\t    ______    ___        _    _________ \n"
            + "\t  .' ___  | .'   `.     / \\  |  _   _  |\n"
            + "\t / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
            + "\t | |   ____| |   | |  / ___ \\    | |    \n"
            + "\t \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
            + "\t  `._____.' `.___.'|____| |____|_____|  \n";
    System.out.println("\t Hello from\n" + logo);

    greet();
    while (true) { // assumes all commands will be correct (errors are a future task)
      String[] input = in.nextLine().trim().split(" ", 2);
      if (input[0].equals("bye")) break;
      executeCommand(input);
    }
    sayBye();
  }

  static void executeCommand(String[] input) {
    String command = input[0];
    String name = "";
    Map<String, String> parsedArguments = new HashMap<>();

    if (input.length > 1) {
      String[] arguments = input[1].split("/");
      name = arguments[0].trim();
      for (int i = 1; i < arguments.length; ++i) {
        String[] argument = arguments[i].split(" ", 2);
        String value = "";
        if (argument.length == 2) value = argument[1].trim();
        parsedArguments.put(argument[0], value);
      }
    }

    try {
      switch (command) {
        case "list":
          listTasks();
          break;
        case "mark":
          markTask(name);
          break;
        case "unmark":
          unmarkTask(name);
          break;
        case "todo":
          addTask(new Todo(name));
          break;
        case "deadline":
          addTask(new Deadline(name, parsedArguments.getOrDefault("by", "")));
          break;
        case "event":
          addTask(
              new Event(
                  name,
                  parsedArguments.getOrDefault("from", ""),
                  parsedArguments.getOrDefault("to", "")));
          break;
        case "delete":
          deleteTask(name);
          break;
        default:
          throw new DukeException("I'm sorry, but I don't know what that means :-(");
      }
    } catch (DukeException e) {
      handleDukeException(e);
    }
  }

  static void deleteTask(String i) {
    Task t = getTaskToModify("delete", i);
    tasks.remove(Integer.parseInt(i)-1);

    printLine();
    System.out.println("\t Noted. I've removed this task:");
    System.out.printf("\t\t %s\n", t);
    printNumberOfTasks();
    printLine();
  }

  static void handleDukeException(DukeException e) {
    printLine();
    System.out.printf("\t ☹ OOPS!!! %s\n", e.getMessage());
    printLine();
  }

  static Task getTaskToModify(String command, String i) {
    try {
      return tasks.get(Integer.parseInt(i) - 1);
    } catch (NumberFormatException e) {
      throw new DukeException(String.format("The argument of %s must be a number.", command));
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(String.format("%s is out of bounds for the tasks list", i));
    }
  }

  static void markTask(String i) {
    Task t = getTaskToModify("mark", i);
    t.mark();

    printLine();
    System.out.println("\t Nice! I've marked this task as done:");
    System.out.printf("\t\t %s\n", t);
    printLine();
  }

  static void unmarkTask(String i) {
    Task t = getTaskToModify("unmark", i);
    t.unmark();

    printLine();
    System.out.println("\t OK, I've marked this task as not done yet");
    System.out.printf("\t\t %s\n", t);
    printLine();
  }

  static void listTasks() {
    printLine();
    System.out.println("\t Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); ++i) System.out.printf("\t %d.%s\n", i + 1, tasks.get(i));
    printLine();
  }

  static void addTask(Task task) {
    tasks.add(task);

    printLine();
    System.out.println("\t Got it. I've added this task:");
    System.out.printf("\t\t %s\n", task);
    printNumberOfTasks();
    printLine();
  }

  static void printNumberOfTasks() {
    System.out.printf(
        "\t Now you have %d task%s in the list.\n", tasks.size(), tasks.size() == 1 ? "" : "s");
  }

  static void greet() {
    printLine();
    System.out.printf("\t Hello! I'm %s\n", botName);
    System.out.println("\t What can I do for you?");
    printLine();
  }

  static void sayBye() {
    printLine();
    System.out.println("\t Bye. Hope to see you again soon!");
    printLine();
  }

  static void printLine() {
    String line = "\t____________________________________________________________";
    System.out.println(line);
  }
}
