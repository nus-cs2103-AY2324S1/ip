import java.util.Scanner;
public class Eva {
    public static void main(String[] args) {
        String logo = "  ______          \n"
                + " |  ____|         \n"
                + " | |____   ____ _ \n"
                + " |  __\\ \\ / / _` |\n"
                + " | |___\\ V / (_| |\n"
                + " |______\\_/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Eva.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");

        String[] tasks = new String[100]; // Array to store tasks
        int taskCount = 0; // Counter for tasks

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("\t____________________________________________________________");
                if (taskCount == 0) {
                    System.out.println("\t No tasks added yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("\t____________________________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + input);
                System.out.println("\t____________________________________________________________");
            }
        }
        scanner.close();
    }
}