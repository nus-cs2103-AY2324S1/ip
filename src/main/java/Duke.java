import java.util.Objects;
import java.util.Scanner;

public class Duke {
    // Greet user
    public static void greet() {
        System.out.println("Hello I'm Oscar! Your friendly chatbot :)");
        System.out.println("What can I do for you?\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int counter = 0;
        boolean running = true;

        greet();

        while (running) {
            // Obtain command entered by user
            String userInput = scanner.nextLine();
            // Exit programme if user enters "bye" command
            if (Objects.equals(userInput, "bye")) {
                System.out.println("Goodbye for now. " +
                        "Hope to see you again soon!");
                running = false;
            } else if (Objects.equals(userInput, "list")) {
                // Display text stored by user in chronological order if
                // user enters "list" command
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= counter; i++) {
                    Task currentTask = taskList[i - 1];
                    System.out.println(i + ".[" + currentTask.getStatusIcon() +
                            "] " + currentTask.getDescription());
                }
                System.out.println();
            } else if (userInput.length() > 7 &&
                    userInput.startsWith("unmark")) {
                // Mark task as not done if user enters "unmark" command
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                Task currentTask = taskList[taskIndex];
                currentTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n");
                System.out.println("[ ] " + currentTask.getDescription());
            } else if (userInput.length() > 5 &&
                    userInput.startsWith("mark")) {
                // Mark task as done if user enters "mark" command
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                Task currentTask = taskList[taskIndex];
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[X] " + currentTask.getDescription() + "\n");
            } else {
                // Store user's command in taskList otherwise
                Task newTask = new Task(userInput);
                taskList[counter] = newTask;
                counter++;
                System.out.println("Oscar added: " + userInput + "\n");
            }
        }
    }
}
