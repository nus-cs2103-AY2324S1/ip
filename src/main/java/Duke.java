import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String chatbotName = "Albatross\n";
        System.out.print("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");

        // Task list to store user responses
        TaskList taskList = new TaskList();

        // Scanner to read user response
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a command");

        // Scanner reads responses and adds to task list
        String userResponse = scanner.nextLine();

        // Scanner reads responses until the user enters "bye"
        // The contents of the task list are printed if the user enters "list"
        while (!userResponse.equals("bye")) {
            if (userResponse.equals("list")) {
                taskList.printTaskList();
            } else {
                taskList.addTask(userResponse);
                System.out.println("added: " + userResponse);
            }
            userResponse = scanner.nextLine();
        }

        System.out.println("Bye! Hope to see you again soon!");
    }
}
