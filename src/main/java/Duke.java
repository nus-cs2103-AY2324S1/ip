import java.util.Scanner;
import java.util.regex.*;

public class Duke {
/*
First take a command, parse to parser, create the necessary task objects, add the task etc
Since each task have different flags, we parse to that task object to parse the remaining themselves
 */

  Task[] lst = new Task[100];
  int count = 0;

  public void horiLine() {
    System.out.println("---------------------");
  }

  public void greet() {
    System.out.println("---------------------");
    System.out.println("Hello! I'm AJbot\n" + "What can I do for you?");
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


  public void askCommand() {
    Scanner scanner = new Scanner(System.in);
    String command = scanner.next().toLowerCase();
//        String front = command.split(" ")[0];
//        String back = command.split(" ")[1];
    if (command.equals("mark")) {
      String back = scanner.next();
      int idx = Integer.parseInt(back) - 1;
      Task task = this.lst[idx];
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
      Task task = this.lst[idx];
      horiLine();
      if (!task.isCompleted()) {
        System.out.println("Task is already unmarked!!!");
      } else {
        task.toggleComplete();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
      }
    } else if (command.equals("list")) {
      if (this.count == 0) {
        System.out.println("No items yet, add something!!!");
      }
      for (int i = 1; i <= this.count; i++) {
        System.out.println(i + "." + this.lst[i - 1]);
      }
    } else if (command.equals("bye")) {
      exit();
      return;
    } else { // if its none of the main commands, then its a task. do logic for parsing here or thr

      String remaining = scanner.nextLine();
      horiLine();

      Task task = null;
      if (command.equals("todo")) {
        task = new Todo(remaining.substring(1));
      } else if (command.equals("deadline")) {
        task = getDeadlineTask(remaining);
      } else if (command.equals("event")) {
        task = getEventTask(remaining);
      } else {
        System.out.println("No such command!!! Try again!");
      }
      if (task != null) { // have a task
        lst[count] = task;
        this.count += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.count);
      }
    }
    horiLine();
    askCommand();
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
    Duke bot = new Duke();
    bot.greet();
    bot.askCommand();
  }
}
