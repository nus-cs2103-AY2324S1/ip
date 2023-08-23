import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm zy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String command = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon() + "]" + " " + tasks[i].getDescription());
                }
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + "[" + tasks[taskIndex].getStatusIcon()
                                    + "]" + " " + tasks[taskIndex].getDescription());
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[taskIndex].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + "["
                                    + tasks[taskIndex].getStatusIcon() + "]" + " " + tasks[taskIndex].getDescription());
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
