import java.util.Scanner;

import commands.Delete;
import commands.List;
import commands.Mark;
import commands.Unmark;
import commands.Bye;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

enum Command {
  LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

  public static Command parse(String input) {
    for (Command command : Command.values()) {
      if (command.toString().toLowerCase().equals(input))
        return command;
    }
    throw new UnsupportedOperationException();
  }
}

public class Jerma {
  private TaskList tasks;
  private Ui ui;

  public Jerma() {
    this.ui = new Ui();

    try {
      this.tasks = Storage.load();
    } catch (Exception e) {
      this.tasks = new TaskList();
    }
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    ui.hello();

    listen: while (true) {
      String input = scanner.nextLine();
      String[] inputArgs = input.split(" ", 2);
      try {
        Command command = Command.parse(inputArgs[0]);

        switch (command) {
        case LIST:
          new List(ui, tasks).execute();
          break;
        case BYE:
          new Bye(ui, tasks, scanner).execute();
          break listen;
        case MARK:
          int index = Integer.parseInt(inputArgs[1]);
          new Mark(ui, tasks, index).execute();
          ;
          break;
        case UNMARK:
          index = Integer.parseInt(inputArgs[1]);
          new Unmark(ui, tasks, index).execute();
          break;
        case DELETE:
          index = Integer.parseInt(inputArgs[1]);
          new Delete(ui, tasks, index).execute();
          break;
        case TODO:
          tasks.add(new Todo(inputArgs[1]));
          System.out.println("added todo: " + inputArgs[1]);
          break;
        case DEADLINE:
          String[] split = inputArgs[1].split(" /by ", 2);
          String description = split[0];
          String by = split[1];

          tasks.add(new Deadline(description, by));
          System.out.println(
              String.format("added deadline: %s by %s", description, by));
          break;
        case EVENT:
          String[] split1 = inputArgs[1].split(" /from ", 2);
          String[] split2 = split1[1].split(" /to ", 2);
          description = split1[0];
          String from = split2[0];
          String to = split2[1];

          tasks.add(new Event(description, from, to));
          System.out.println(String.format("added event: %s from %s to %s",
              description, from, to));
          break;
        }
      } catch (IndexOutOfBoundsException e) {
        ui.error("Invalid arguments. Try again!");
      } catch (UnsupportedOperationException e) {
        ui.error("Invalid command. Try again!");
      }
    }
  }

  public static void main(String[] args) {
    new Jerma().run();
  }
}
