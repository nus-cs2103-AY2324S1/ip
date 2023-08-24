import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Axela");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (command.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else {
                String[] parts = command.split(" ", 2);
                String taskType = parts[0].toLowerCase();

                if (taskType.equals("todo")) {
                    tasks[taskCount] = new TodoTask(parts[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (taskType.equals("deadline")) {
                    tasks[taskCount] = new DeadlineTask(parts[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (taskType.equals("event")) {
                    tasks[taskCount] = new EventTask(parts[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]);
                    } else {
                        System.out.println(" Invalid task index.");
                    }
                } else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]);
                    } else {
                        System.out.println(" Invalid task index.");
                    }
                } else {
                    System.out.println(" Invalid command.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
            }

            System.out.println("____________________________________________________________");
        }
    }
}