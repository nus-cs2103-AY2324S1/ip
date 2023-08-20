import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main program.
 */
public class Alyssa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String logo = " ___  __    __   __  ____   ____    ___\n"
                    + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
                    + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
                    + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
                    + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
                    + "|   | |_____| |_|    |____| |____| |   |\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
        List<Task> taskList = new ArrayList<>();
        while (true) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else if (nextInput.equals("list")) {
                int counter = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(counter + "." + task.toString());
                    counter++;
                }
                System.out.println(line);
            } else if (nextInput.startsWith("mark")) {
                String[] argumentArray = nextInput.split(" ");
                try {
                    int index = Integer.valueOf(argumentArray[1]);
                    if (index < 1 || index > taskList.size()) {
                        throw new Exception("Invalid index");
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.toString());
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
            } else if (nextInput.startsWith("unmark")) {
                String[] argumentArray = nextInput.split(" ");
                try {
                    int index = Integer.valueOf(argumentArray[1]);
                    if (index < 1 || index > taskList.size()) {
                        throw new Exception("Invalid index");
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsUndone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.toString());
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
            } else {
                taskList.add(new Task(nextInput));
                System.out.println(line);
                System.out.println("added: " + nextInput);
                System.out.println(line);
            }
        }
    }
}
