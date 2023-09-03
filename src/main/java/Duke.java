import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        tasks.loadTasksFromFile();
        Runtime.getRuntime().addShutdownHook(new Thread(tasks::saveTasksToFile));

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
                    tasks.saveTasksToFile();
                    System.out.print(tasks);
                } else if (command.startsWith("mark")) {
                    tasks.markAsDone(command);
                    tasks.saveTasksToFile();
                } else if (command.startsWith("unmark")) {
                    tasks.markAsNotDone(command);
                    tasks.saveTasksToFile();
                } else if (command.startsWith("delete")) {
                    tasks.deleteTask(command);
                    tasks.saveTasksToFile();
                } else {
                    tasks.processCommand(command);
                    tasks.saveTasksToFile();
                }
            } catch (DukeException e) {
                System.out.println(" " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }
    }
}