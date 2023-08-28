import commands.*;
import utility.PrintUtility;

import java.util.List;
import java.util.Scanner;

public class Duke {
  private static ICommand dispatchCommand(String command) {
    switch (command) {
      case "todo":
        return new AddTodoCommand();
      case "deadline":
        return new AddDeadlineCommand();
      case "event":
        return new AddEventCommand();
      case "mark":
        return new MarkTaskCommand();
      case "unmark":
        return new UnmarkTaskCommand();
      case "list":
        return new ListTasksCommand();
      case "delete":
        return new DeleteTaskCommand();
      default:
        return new UnknownCommand();
    }
  }

  public static void main(String[] args) {
    PrintUtility.printText("Hello! I'm Cyrus", "What can I do for you?");
    String input;
    Scanner sc = new Scanner(System.in);
    while (true) {
      input = sc.nextLine().trim();

      // Handle empty inputs
      if (input.equals("")) {
        PrintUtility.printText("Enter a command please!");
        continue;
      }

      List<String> parts = List.of(input.split(" "));
      String command = parts.get(0);
      if (command.equals("bye")) break;

      ICommand commandToRun = dispatchCommand(command);
      commandToRun.execute(parts);
    }

    PrintUtility.printText("Bye. Hope to see you again soon!");
  }
}
