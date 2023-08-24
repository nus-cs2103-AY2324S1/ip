import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    /** Divider constant */
    private static final String DIVIDER = "      ____________________________________________________________";

    /**
     * Method that marks a given task in a list
     * @param list List containing user inputs.
     * @param index Index of the given task.
     */
    public static void markAsDone(List<Task> list, int index) {
        if (index >= 0 && index <= list.size()) {
            Task task = list.get(index);
            task.markAsDone();

            System.out.println(DIVIDER);
            System.out.println("        Nice! I've marked this task as done:");
            System.out.println("            " + task);
            System.out.println(DIVIDER);

        } else {
            System.out.println(DIVIDER);
            System.out.println("        Oops! Task not found!");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Method that unmark a given task in a list
     * @param list List containing user inputs.
     * @param index Index of the given task.
     */
    public static void markAsUndone(List<Task> list, int index) {
        if (index >= 0 && index <= list.size()) {
            Task task = list.get(index);
            task.markAsUndone();

            System.out.println(DIVIDER);
            System.out.println("         OK! I've marked this task as not done yet:");
            System.out.println("            " + task);
            System.out.println(DIVIDER);

        } else {
            System.out.println(DIVIDER);
            System.out.println("        Oops! Task not found!");
            System.out.println(DIVIDER);
        }
    }

    public static void main(String[] args) {
        // Opening lines
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);

        // Check user input
        Scanner sc = new Scanner(System.in);
        List<Task> userList = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // If user wants to quit
                System.out.println(DIVIDER);
                System.out.println("        Bye ~ Hope to see you again soon ~");
                System.out.println(DIVIDER);
                break;

            } else if (userInput.equals("list")) {
                // If user wants to check list
                System.out.println(DIVIDER);
                System.out.println("        Sure! Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    String str = String.format("            %d. %s", i + 1, userList.get(i));
                    System.out.println(str);
                }
                System.out.println(DIVIDER);

            } else if (userInput.startsWith("mark")) {
                // If user wants to mark something
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        markAsDone(userList, index);
                    } catch (NumberFormatException e) {
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid task number!");
                        System.out.println(DIVIDER);
                    }
                } else {
                    // Invalid command format
                    System.out.println(DIVIDER);
                    System.out.println("        Oops! Invalid command format!");
                    System.out.println(DIVIDER);
                }

            } else if (userInput.startsWith("unmark")) {
                // If user wants to unmark something
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        markAsUndone(userList, index);
                    } catch (NumberFormatException e) {
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid task number!");
                        System.out.println(DIVIDER);
                    }
                } else {
                    // Invalid command format
                    System.out.println(DIVIDER);
                    System.out.println("        Oops! Invalid command format!");
                    System.out.println(DIVIDER);
                }

            } else {
                // Other inputs
                Task task = new Task(userInput);
                userList.add(task);

                System.out.println(DIVIDER);
                System.out.println("        Added: " + userInput);
                System.out.println(DIVIDER);
            }
        }
    }
}