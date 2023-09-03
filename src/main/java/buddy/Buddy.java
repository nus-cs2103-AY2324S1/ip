package buddy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "Task Buddy";
    private List<Task> taskList;
    private final String filePath = "./data/tasks.txt";

    public static void main(String[] args) throws BuddyException {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        String command;
        TaskList tasks = new TaskList();
        tasks.loadTasks();

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.print(tasks);
            } else if (command.startsWith("mark") || command.startsWith("unmark")
                    || command.startsWith("delete")) {
                String[] arrOfCmd = command.split(" ");
                Integer taskIndex = Integer.valueOf(arrOfCmd[1]) - 1;

                try {
                    // Task thisTask = tasks.getTask(taskIndex);
                    if (command.startsWith("mark")) {
                        tasks.markAsDone(taskIndex);
                        tasks.saveTasks();
                    }
                    if (command.startsWith("unmark")) {
                        tasks.markAsNotDone(taskIndex);
                        tasks.saveTasks();
                    }
                    if (command.startsWith("delete")) {
                        tasks.deleteTask(taskIndex);
                        tasks.saveTasks();
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number.");
                }

            } else {
                tasks.processCommand(command);
                tasks.saveTasks();
            }
        }
    }
}
