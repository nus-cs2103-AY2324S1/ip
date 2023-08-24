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
            } else if (echo.startsWith("todo")) {
                String description = echo.substring(5).trim();
                taskList[taskCount] = new ToDo(description, 'T');
                taskCount++;
                System.out.println(" -----------------------------------------");
                System.out.println("     Got it. I've added this task:");
                System.out.println("    " + taskList[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
                System.out.println(" -----------------------------------------");
            } else if (echo.startsWith("deadline")) {
                int byIndex = echo.indexOf("/by");
                String description = echo.substring(9, byIndex).trim();
                String by = echo.substring(byIndex + 3).trim();
                taskList[taskCount] = new Deadline(description, by, 'D');
                taskCount++;
                System.out.println(" -----------------------------------------");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + taskList[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
                System.out.println(" -----------------------------------------");
            } else if (echo.startsWith("event")) {
                int fromIndex = echo.indexOf("/from");
                int toIndex = echo.indexOf("/to");
                String description = echo.substring(6, fromIndex).trim();
                String from = echo.substring(fromIndex + 5, toIndex).trim();
                String to = echo.substring(toIndex + 3).trim();
                taskList[taskCount] = new Event(description, from, to, 'E');
                taskCount++;
                System.out.println(" -----------------------------------------");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + taskList[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
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
                System.out.println(" -------------------------------------------");
                System.out.println("     I'm sorry, I don't understand that command.");
                System.out.println(" -------------------------------------------");
            }
        }

        scanner.close();

    }
}
