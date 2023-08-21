import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "________________________________________________________________";
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(horizontalLine);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                taskCount = addTask(tasks, taskCount, userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println(horizontalLine);
        }
        scanner.close();
    }

    public static int addTask(String[] tasks, int count, String task) {
        tasks[count] = task;
        return count + 1;
    }
}
