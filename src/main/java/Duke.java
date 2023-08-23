import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Duke {

  static final String botName = "GOAT";
  static final Scanner in = new Scanner(System.in);
  static final List<Task> tasks = new ArrayList<>();
	static final Printer out = new Printer();

  public static void main(String[] args) {
   greet();
    while (true) { 
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
          throw new DukeException("I'm sorry, but I don't know what that means :-(.");
      }
    } catch (DukeException e) {
      handleDukeException(e);
    }
  }

  static void deleteTask(String i) {
    Task t = getTaskToModify("delete", i);
    tasks.remove(Integer.parseInt(i)-1);

		out.print("Noted. I've removed this task:", t, getNumberOfTasksString());
  }

  static void handleDukeException(DukeException e) {
		out.print(String.format("☹ OOPS!!! %s", e.getMessage()));
  }

  static Task getTaskToModify(String command, String i) {
    try {
      return tasks.get(Integer.parseInt(i) - 1);
    } catch (NumberFormatException e) {
      throw new DukeException(String.format("The argument of %s must be a number.", command));
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(String.format("%s is out of bounds for the tasks list.", i));
    }
  }

  static void markTask(String i) {
    Task t = getTaskToModify("mark", i);
    t.mark();

		out.print("Nice! I've marked this task as done:", t);
  }

  static void unmarkTask(String i) {
    Task t = getTaskToModify("unmark", i);
    t.unmark();

		out.print("Ok, I've marked this task as not done yet", t);
	}

  static void listTasks() {
		Object toPrint[] = new Object[tasks.size()+1];
		toPrint[0] = "Here are the tasks in your list:";
		for(int i=0;i<tasks.size();++i) toPrint[i+1] = String.format("%d.%s", i+1, tasks.get(i));
		out.print(toPrint);
  }

  static void addTask(Task task) {
    tasks.add(task);

		out.print("Got it. I've added this task:", task, getNumberOfTasksString());
  }

  static String getNumberOfTasksString() {
		return String.format("Now you have %d task%s in the list.", tasks.size(), tasks.size() == 1 ? "" : "s");
  }

  static void greet() {
    String logo =
        "        ______    ___        _    _________ \n"
            + "      .' ___  | .'   `.     / \\  |  _   _  |\n"
            + "     / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
            + "     | |   ____| |   | |  / ___ \\    | |    \n"
            + "     \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
            + "      `._____.' `.___.'|____| |____|_____|  \n";

 
		out.print(String.format("Hello from\n%s", logo), String.format("Hello! I'm %s", botName));
  }

  static void sayBye() {
		out.print("Bye. Hope to see you again soon!");
  }

	static void printLine() {}
}
