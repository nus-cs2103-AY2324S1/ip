import java.util.Scanner;

import commands.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
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
          new List(tasks, ui).execute();
          break;
        case BYE:
          break listen;
        case MARK:
          int index = Integer.parseInt(inputArgs[1]) - 1;
          Task task = tasks.get(index);
          task.setDone();

          System.out.println("Marked as done: \n" + task);
          break;
        case UNMARK:
          index = Integer.parseInt(inputArgs[1]) - 1;
          task = tasks.get(index);
          task.setUndone();

          System.out.println("Marked as undone: \n" + task);
          break;
        case DELETE:
          index = Integer.parseInt(inputArgs[1]) - 1;
          Task removed = tasks.remove(index);

          System.out.println(String.format(
              "Removed the task: \n%s \nYou have %d tasks remaining.", removed,
              tasks.size()));
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
        System.out.println("Invalid arguments. Try again!");
      } catch (UnsupportedOperationException e) {
        System.out.println("Invalid command. Try again!");
      }
    }
    scanner.close();
    Storage.save(tasks);
  }

  public static void main(String[] args) {
    new Jerma().run();
  }
}
