import java.util.Scanner;
public class Duke {
    final static String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        // Greetings
        System.out.println(LINE +
                " Hello! I'm Anya Forger\n" +
                " What can I do for you?\n" +
                LINE);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            command = sc.next();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(LINE);
                System.out.println(" Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }

                System.out.println(LINE);
            } else if (command.equals("mark")) {
                int taskIndex = sc.nextInt() - 1;
                Task t = tasks[taskIndex];
                t.markAsDone();

                System.out.println(LINE);
                System.out.println(" Nice! I've marked this task as Done:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else if (command.equals("unmark")){
                int taskIndex = sc.nextInt() - 1;
                Task t = tasks[taskIndex];
                t.markAsNotDone();

                System.out.println(LINE);
                System.out.println(" Nice! I've marked this task as Not Done:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else if (command.equals("todo")) {
                String todo = sc.nextLine().trim();
                Task t = new Todo(todo);
                tasks[taskCount++] = t;

                System.out.println(LINE);
                System.out.println(" Got it! I've added this task:");
                System.out.println(t);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            } else if (command.equals("deadline")) {
                String deadline = sc.nextLine();
                String[] details = deadline.split("/");

                String task = details[0].trim();
                String by = details[1].replace("by", "").trim();

                Task t = new Deadline(task, by);
                tasks[taskCount++] = t;

                System.out.println(LINE);
                System.out.println(" Got it! I've added this task:");
                System.out.println(t);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            } else if (command.equals("event")) {
                String event = sc.nextLine();
                String[] details = event.split("/");

                String task = details[0].trim();
                String from = details[1].replace("from", "").trim();
                String to = details[2].replace("to", "").trim();

                Task t = new Event(task, from, to);
                tasks[taskCount++] = t;

                System.out.println(LINE);
                System.out.println(" Got it! I've added this task:");
                System.out.println(t);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            } else {
                Task t = new Task(command);
                tasks[taskCount++] = t;

                System.out.println(LINE);
                System.out.println(" Got it! I've added this task:");
                System.out.println(t);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            }
        }

        // Exit
        System.out.println(LINE +
                " Bye. Hope to see you again soon!\n" +
                LINE);
    }
}
