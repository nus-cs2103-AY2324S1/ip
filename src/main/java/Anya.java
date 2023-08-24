import java.util.ArrayList;
import java.util.Scanner;
public class Anya {
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

        ArrayList<Task> tasks1= new ArrayList<>();

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
                if (!sc.hasNextInt()) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! Please specify the task number to marked as Done");
                    System.out.println(LINE);
                    continue;
                }

                int taskIndex = sc.nextInt() - 1;
                Task t = tasks[taskIndex];
                t.markAsDone();

                System.out.println(LINE);
                System.out.println(" Nice! I've marked this task as Done:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else if (command.equals("unmark")) {
                if (!sc.hasNextInt()) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! Please specify the task number to marked as Not Done");
                    System.out.println(LINE);
                    continue;
                }

                int taskIndex = sc.nextInt() - 1;
                Task t = tasks[taskIndex];
                t.markAsNotDone();

                System.out.println(LINE);
                System.out.println(" Nice! I've marked this task as Not Done:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else if (command.equals("todo")) {
                if (!sc.hasNext()) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(LINE);
                    break;
                }

                String todo = sc.nextLine().trim();
                Task t = new Todo(todo);
                tasks[taskCount++] = t;

                System.out.println(LINE);
                System.out.println(" Got it! I've added this task:");
                System.out.println(t);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(LINE);
            } else if (command.equals("deadline")) {
                if (!sc.hasNextLine()) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(LINE);
                    continue;
                }

                String deadline = sc.nextLine();
                if (!deadline.contains("/by")) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! Please specify the deadline of this task using /by ");
                    System.out.println(LINE);
                    continue;
                }

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
                if (!sc.hasNextLine()) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(LINE);
                    continue;
                }

                String event = sc.nextLine();

                if (!event.contains("/from")) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! Please specify the start time of this task using /from ");
                    System.out.println(LINE);
                    continue;
                }

                if (!event.contains("/to")) {
                    System.out.println(LINE);
                    System.out.println("☹ OOPS!!! Please specify the end time of this task using /to ");
                    System.out.println(LINE);
                    continue;
                }

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
                System.out.println(LINE);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(LINE);
            }
        }

        // Exit
        System.out.println(LINE +
                " Bye. Hope to see you again soon!\n" +
                LINE);
    }
}
