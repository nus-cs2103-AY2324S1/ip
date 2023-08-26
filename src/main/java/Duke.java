import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Axela");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            try {
                if (command.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    System.out.println(" Here are the tasks in your list:");
                    System.out.print(tasks);
                } else if (command.startsWith("mark")) {
                    tasks.markAsDone(command);
                } else if (command.startsWith("unmark")) {
                    tasks.markAsNotDone(command);
                } else if (command.startsWith("delete")) {
                    tasks.deleteTask(command);
                } else {
                    tasks.processCommand(command);
                }
            } catch (DukeException e) {
                System.out.println(" " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }
    }
}