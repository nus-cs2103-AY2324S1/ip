import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

            if ("bye".equalsIgnoreCase(userInput)) {
                break; // Exit the loop if the user types "bye"
            }

            if ("list".equalsIgnoreCase(userInput)) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task.description);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark ")) {
                processTask(userInput, tasks, true);
            } else if (userInput.startsWith("unmark ")) {
                processTask(userInput, tasks, false);
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
                tasks.add(new Task(userInput));
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            Task task = tasks.get(taskNumber - 1);
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }
}

