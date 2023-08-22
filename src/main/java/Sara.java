import java.util.Scanner;
public class Sara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

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

            } else if ("list".equalsIgnoreCase(userInput)) {
                taskManager.printTasks();
            } else if (userInput.startsWith("mark")){
                int index = Integer.parseInt(userInput.split(" ")[1]);
                taskManager.taskDone((index));
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + taskManager.getTask(index));
                System.out.println("    ____________________________________________________________");

            } else if (userInput.startsWith("umark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                taskManager.unMarktask(index);
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + taskManager.getTask(index));
                System.out.println("    ____________________________________________________________");
            } else {
                taskManager.addTask(userInput);
                System.out.println("     added: " + userInput);
                System.out.println("    ____________________________________________________________");

            }
        }

        scanner.close();
    }
}

