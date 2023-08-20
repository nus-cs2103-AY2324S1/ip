import java.util.Scanner;

public class Duke {

  public class Task {
    protected boolean completed;
    protected String description;

//        public String getStatusIcon() {
//            return (this.completed ? "X" : " "); // mark done task with X
//        }

    public void toggleComplete() {
      this.completed = !this.completed;
    }

    public boolean isCompleted() {
      return this.completed;
    }

    @Override
    public String toString() {
      return (this.completed ? "[X] " : "[ ] ") + this.description;
    }

    Task(String description) {
      this.description = description;
      this.completed = false;
    }
  }

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
    } else { // if its none of the main commands, then its a task
      String back = scanner.next();
      horiLine();
      Task task = new Task(command + " " + back); // create a task
      lst[count] = task;
      count += 1;
      System.out.println(count);
      System.out.println("added: " + task);
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
