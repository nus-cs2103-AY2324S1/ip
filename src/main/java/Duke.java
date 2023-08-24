import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int taskCount = 0;

        // Greeting message
        System.out.println("Hello! I'm Sivraj");
        System.out.println("What can I do for you?");

        while (true) {
            String echo = scanner.nextLine();

            if (echo.equals("bye")) {
                // Farewell message
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (echo.equals("list")) {
                System.out.println(" ----------------------------------------");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList[i]);
                }
                System.out.println(" -----------------------------------------");
            } else if (echo.startsWith("mark")) {
                int taskIndex = Integer.parseInt(echo.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    taskList[taskIndex].markAsDone();
                    System.out.println(" ------------------------------------------");
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("       " + taskList[taskIndex]);
                    System.out.println(" ------------------------------------------");
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (echo.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(echo.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    taskList[taskIndex].markAsNotDone();
                    System.out.println(" -------------------------------------------");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + taskList[taskIndex]);
                    System.out.println(" -------------------------------------------");
                } else {
                    System.out.println("Invalid task index.");
                }
            } else {
                taskList[taskCount] = new Task(echo);
                taskCount++;

                System.out.println(" ----------------------------------------");
                System.out.println("    added: " + echo);
                System.out.println(" ----------------------------------------");
            }
        }

        scanner.close();

    }
}
