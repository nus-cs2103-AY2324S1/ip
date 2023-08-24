import java.util.Scanner;

public class Dukduk {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();

        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" ");
            String input = scanner.nextLine();
            printLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(" No tasks added yet.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon()
                                + "] " + tasks[i].getDescription());
                    }
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:\n ["
                            + tasks[taskIndex].getStatusIcon() + "] "
                            + tasks[taskIndex].getDescription());
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].unmark();
                    System.out.println(" OK, I've marked this task as not done yet:\n ["
                            + tasks[taskIndex].getStatusIcon() + "] "
                            + tasks[taskIndex].getDescription());
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(" added: " + input);
            }
            printLine();
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

