import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
  LIST, BYE, MARK, UNMARK, ADD;

  public static Command parse(String input) {
    for (Command command : Command.values()) {
      if (command.toString().toLowerCase().equals(input))
        return command;
    }
    return Command.ADD;
  }
}

public class Jerma {
  public static void main(String[] args) {
    System.out.println("Hello! I'm Jerma.");

    Scanner scanner = new Scanner(System.in);
    List<Task> toDoList = new ArrayList<>();

    listen: while (true) {
      String input = scanner.nextLine();
      String[] inputArgs = input.split(" ");
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
      case ADD:
        toDoList.add(new Task(input));
        System.out.println("added: " + input);
        break;
      }
    }
    scanner.close();

    System.out.println("See ya soon!");
  }
}
