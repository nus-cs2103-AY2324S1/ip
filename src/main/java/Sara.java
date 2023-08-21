import java.util.Scanner;
public class Sara {

    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Sara");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;

            } else if("list".equalsIgnoreCase(userInput)) {
                listTask();
            } else {
                addTask(userInput);
                System.out.println("     added: " + userInput);
                System.out.println("    ____________________________________________________________");

            }
        }

        scanner.close();
    }

    private static void addTask (String t) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = t;
            taskCount++;
        } else {
            System.out.println("     Error: Task list is full!");
        }
    }

    private static void listTask() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }
}