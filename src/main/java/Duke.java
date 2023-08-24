import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ██▄   ████▄    ▄     ▄▀  \n" +
                "█  █  █   █     █  ▄▀    \n" +
                "█   █ █   █ ██   █ █ ▀▄  \n" +
                "█  █  ▀████ █ █  █ █   █ \n" +
                "███▀        █  █ █  ███  \n" +
                "            █   ██       \n" +
                "                         ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                if ("list".equals(input)) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < inputs.size()+1; i++) {
                        Task task = inputs.get(i - 1);
                        System.out.println(i + ". " + task.getStatusAndDescription());
                    }
                } else if (input.contains("mark")) {
                    String[] parts = input.split(" "); // Splitting the string by space
                    String lastPart = parts[parts.length - 1]; // Taking the last part
                    try {
                        int number = Integer.parseInt(lastPart); // Parsing the last part as an integer
                        Task task = inputs.get(number-1);
                        if (input.contains("unmark")) {
                            System.out.println("OK, I've marked this task as not done yet:");
                            task.markAsNotDone();
                        }  else if (input.contains("mark")) {
                            System.out.println("Nice! I've marked this task as done:");
                            task.markAsDone();
                        }
                        System.out.println("  " + task.getStatusAndDescription());
                    } catch (NumberFormatException e) {
                        System.out.println("No number found at the end of the string.");
                    }
                } else {
                    Task newTask = new Task(input);
                    inputs.add(newTask);
                    System.out.println("added: " + newTask.getDescription());
                }
            }
        }

        // invalid inputs, invalid numbers, invalid commands

        scanner.close();

    }
}
