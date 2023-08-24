import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] taskList = new String[100];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList[i]);
                }
                System.out.println(" -----------------------------------------");
            } else {
                taskList[taskCount] = echo;
                taskCount++;

                System.out.println(" ----------------------------------------");
                System.out.println("    added: " + echo);
                System.out.println(" ----------------------------------------");
            }
        }

        scanner.close();

    }
}
