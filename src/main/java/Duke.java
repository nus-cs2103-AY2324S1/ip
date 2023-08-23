import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int number = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println("____________________________________________________________");

        boolean loggedIn = true;

        while (loggedIn) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                loggedIn = false;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("List:");
                for (int i = 0; i < number; i ++ ) {
                    System.out.println("Task " + (i + 1)+ ": " + taskList.get(i) + "\n");
                }
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    System.out.println(taskIndex);
                    System.out.println(number);
                    if (taskIndex >= 0 && taskIndex < number) {
                        taskList.get(taskIndex).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Good job! I have marked this task as completed:");
                        System.out.println("  " + taskList.get(taskIndex));
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Sorry! you have input an invalid task!");
                    }
                }
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < number) {
                        taskList.get(taskIndex).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I have marked this as undone:");
                        System.out.println("  " + taskList.get(taskIndex));
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Sorry! you have input an invalid task!");
                    }
                }
            } else {
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                number++;
                System.out.println("Item added: " + userInput + "\n");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}

