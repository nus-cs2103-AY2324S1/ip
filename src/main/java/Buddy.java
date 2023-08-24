import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "To Do Buddy";

    public static void main(String[] args) {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        String command;
        Task t;
        List<Task> taskList = new ArrayList();

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(exit);
                break;
            }
            if (command.equals("list")) {
                printList(taskList);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                String[] arrOfCmd = command.split(" ");
                Integer taskNumber = Integer.valueOf(arrOfCmd[1]) - 1;
                if (taskNumber < 0 || taskNumber > taskList.size()) {
                    System.out.println("Invalid task number.");
                } else {
                    Task thisTask = taskList.get(taskNumber);
                    if (command.startsWith("mark")) {
                        thisTask.markTaskAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    }
                    if (command.startsWith("unmark")) {
                        thisTask.markTaskAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("  [" + thisTask.getStatusIcon() + "]" + thisTask.toString());
                }
            } else {
                t = new Task(command);
                taskList.add(t);
                System.out.println("added: " + command);
            }
        }
    }
    private static void printList(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task);
        }
    }
}
