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
        System.out.println("____________________________________________________________\n");

        boolean loggedIn = true;

        while (loggedIn) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                loggedIn = false;
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < number; i ++ ) {

                    System.out.println("Task " + (i + 1)+ ": " + taskList.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                taskList.add(new Todo(description));
                number++;

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this to-do task:");
                System.out.println("  " + taskList.get(taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");

            } else if (userInput.startsWith("event")) {
                boolean wrongInput = false;
                String input = userInput.substring(6);
                String[] parts = input.split("/from");
                if (parts.length == 2) {
                    String description = parts[0].trim();
                    String[] timeParts = parts[1].split("/to");
                    if (timeParts.length == 2) {
                        String from = timeParts[0].trim();
                        String end = timeParts[1].trim();
                        taskList.add(new Event(description, from, end));
                        number++;
                    } else {
                        wrongInput = true;
                    }
                } else {
                    wrongInput = true;
                }

                if (wrongInput == true) {
                    System.out.println("Please input a valid task");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this event:");
                    System.out.println("  " + taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
            } else if (userInput.startsWith("deadline")) {
                boolean wrongInput = false;
                String input = userInput.substring(9);
                String[] parts = input.split("/by");
                if (parts.length == 2) {
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    taskList.add(new Deadline(description, by));
                    number++;

                } else {
                    wrongInput = true;
                }

                if (wrongInput == true) {
                    System.out.println("Please input a valid task");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this deadline:");
                    System.out.println("  " + taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < number) {
                        taskList.get(taskIndex).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Good job! I have marked this task as completed:");
                        System.out.println("  " + taskList.get(taskIndex));
                        System.out.println("____________________________________________________________\n");
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
                        System.out.println("____________________________________________________________\n");
                    } else {
                        System.out.println("Sorry! you have input an invalid task!");
                    }
                }
            } else {
                System.out.println("Please input valid command");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}

