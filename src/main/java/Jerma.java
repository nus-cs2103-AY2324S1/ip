import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
  LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, ECHO;

  public static Command parse(String input) {
    for (Command command : Command.values()) {
      if (command.toString().toLowerCase().equals(input))
        return command;
    }
    return ECHO;
  }
}

public class Jerma {
  public static void main(String[] args) {
    System.out.println("Hello! I'm Jerma.");

    Scanner scanner = new Scanner(System.in);
    List<Task> toDoList = new ArrayList<>();

    listen: while (true) {
      String input = scanner.nextLine();
      String[] inputArgs = input.split(" ", 2);
      Command command = Command.parse(inputArgs[0]);

      switch (command) {
      case LIST:
        for (int i = 0; i < toDoList.size(); i++) {
          String output = String.format("%d. %s", i + 1, toDoList.get(i));
          System.out.println(output);
        }
        break;
      case BYE:
        break listen;
      case MARK:
        try {
          int index = Integer.parseInt(inputArgs[1]) - 1;
          Task task = toDoList.get(index);
          task.setDone();

          System.out.println("Marked as done: \n" + task);
        } catch (Exception e) {
          System.out.println("Invalid arguments. Try again!");
        }
        break;
      case UNMARK:
        try {
          int index = Integer.parseInt(inputArgs[1]) - 1;
          Task task = toDoList.get(index);
          task.setUndone();

          System.out.println("Marked as undone: \n" + task);
        } catch (Exception e) {
          System.out.println("Invalid arguments. Try again!");
        }
        break;
      case TODO:
        toDoList.add(new Todo(input));
        System.out.println("added todo: " + inputArgs[1]);
        break;
      case DEADLINE:
        String description, by;
        try {
          String[] split = inputArgs[1].split(" /by ", 2);
          description = split[0];
          by = split[1];
        } catch (Exception e) {
          System.out.println("Invalid arguments. Try again!");
          break;
        }
        toDoList.add(new Deadline(description, by));
        System.out.println(
            String.format("added deadline: %s by %s", description, by));
        break;
      case EVENT:
        String from, to;
        try {
          String[] split1 = inputArgs[1].split(" /from ", 2);
          String[] split2 = split1[1].split(" /to ", 2);
          description = split1[0];
          from = split2[0];
          to = split2[1];
        } catch (Exception e) {
          System.out.println("Invalid arguments. Try again!");
          break;
        }
        toDoList.add(new Event(description, from, to));
        System.out.println(String.format("added todo: %s from %s to %s",
            description, from, to));
        break;
      case ECHO:
        System.out.println(input);
      }
    }
    scanner.close();

    System.out.println("See ya soon!");
  }
}
