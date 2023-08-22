import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Aj {
/*
First take a command, parse to parser, create the necessary task objects, add the task etc
Since each task have different flags, we parse to that task object to parse the remaining themselves
 */

//  Task[] lst = new Task[100];

  List<Task> lst = new ArrayList<>();
//  int count = 0;

  public void horiLine() {
    System.out.println("---------------------");
  }

  public void greet() {
    System.out.println("---------------------");
    System.out.println("Hello! I'm Aj\n" + "What can I do for you?");
    horiLine();
  }

  public Task getDeadlineTask(String remaining) { // takes in command, parse it and return task object
    String pattern = " (.*) /by (.*)";

    Pattern regexPattern = Pattern.compile(pattern);
    Matcher matcher = regexPattern.matcher(remaining);

    if (matcher.matches()) {
      String taskName = matcher.group(1);
      String by = matcher.group(2);
      return new Deadline(taskName, by);
    }
    return null;
  }

  public Task getEventTask(String remaining) { // takes in command, parse it and return task object
    String pattern = " (.*) /from (.*?) /to (.*)";

    Pattern regexPattern = Pattern.compile(pattern);
    Matcher matcher = regexPattern.matcher(remaining);

    if (matcher.matches()) {
      String taskName = matcher.group(1);
      String startTime = matcher.group(2);
      String endTime = matcher.group(3);
      return new Event(taskName, startTime, endTime);
    }
    return null;
  }

  public void checkIndex(int val) throws IndexOutOfRangeException { // throws error if index invalid
    if (val <= this.lst.size() - 1 && val >= 0) {
      return;
    } else {
      throw new IndexOutOfRangeException(this.lst.size());
    }
  }

  public void printNoTask() {
    System.out.printf("Now you have %d tasks in the list.\n", this.lst.size());
  }

  public boolean askCommand(Scanner scanner) throws NoSuchCommandException, EmptyDescriptionException, IndexOutOfRangeException {
    // returns true if 'bye' is called
//    System.out.println("Calling");
    String command = scanner.next().toLowerCase();
//        String front = command.split(" ")[0];
//        String back = command.split(" ")[1];
    if (command.equals("mark")) {
      String back = scanner.next();
      int idx = Integer.parseInt(back) - 1;
      checkIndex(idx);
//      Task task = this.lst[idx];
      Task task = this.lst.get(idx);
      horiLine();
      if (task.isCompleted()) {
        System.out.println("You have already marked it!!!");
      } else {
        task.toggleComplete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
      }
    } else if (command.equals("unmark")) {
      String back = scanner.next();
      int idx = Integer.parseInt(back) - 1;
      checkIndex(idx);
//      Task task = this.lst[idx];
      Task task = this.lst.get(idx);
      horiLine();
      if (!task.isCompleted()) {
        System.out.println("Task is already unmarked!!!");
      } else {
        task.toggleComplete();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
      }
    } else if (command.equals("list")) {
      if (this.lst.size() == 0) {
        System.out.println("No items yet, add something!!!");
      }
      for (int i = 1; i <= this.lst.size(); i++) {
//        System.out.println(i + "." + this.lst[i - 1]);
        System.out.println(i + "." + this.lst.get(i - 1));
      }
    } else if (command.equals("delete")) {
      String back = scanner.next();
      int idx = Integer.parseInt(back) - 1;
      checkIndex(idx);
      horiLine();
      System.out.println("Noted. I've removed this task:");
      Task removedTask = this.lst.get(idx);
      this.lst.remove(idx);
      System.out.println(removedTask);
      printNoTask();
    } else if (command.equals("bye")) {
      exit();
      return true;
    } else { // if its none of the main commands, then its a task. do logic for parsing here or thr

      String remaining = scanner.nextLine();
      horiLine();

      Task task = null;
      if (command.equals("todo")) {
        checkMessage(command, remaining);
        task = new Todo(remaining.substring(1));
      } else if (command.equals("deadline")) {
        checkMessage(command, remaining);
        task = getDeadlineTask(remaining);
      } else if (command.equals("event")) {
        checkMessage(command, remaining);
        task = getEventTask(remaining);
      } else {
//        System.out.println("No such command!!! Try again!");
        throw new NoSuchCommandException();
      }
      if (task != null) { // have a task
//        lst[count] = task;
        this.lst.add(task);
//        this.count += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNoTask();
      }
    }
    horiLine();
    return false;
  }

  public void checkMessage(String cmd, String msg) throws EmptyDescriptionException {
    String helpMessage;
    if (msg.length() == 0) {
      if (cmd.equals("todo")) {
        helpMessage = "todo <task name>";
      } else if (cmd.equals("deadline")) {
        helpMessage = "deadline <task name> /by <date/time>";
      } else if (cmd.equals("event")) {
        helpMessage = "event <task name> /from <date/time> /to <date/time>";
      } else {
        helpMessage = "";
      }
      throw new EmptyDescriptionException(cmd, helpMessage);
    }
  }

  public void exit() {
    System.out.println("Bye. Hope to see you again soon!");
    horiLine();
  }

  public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    Aj bot = new Aj();
    bot.greet();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      try {
        if (bot.askCommand(scanner)) {
          break;
        }
      } catch (AjException e) {
        System.out.println(e.getMessage());
        bot.horiLine();
      }
    }
  }
}
