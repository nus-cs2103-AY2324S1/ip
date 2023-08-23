import java.util.Scanner;
public class Duke {
    /**
     * Checks if a string is numeric.
     *
     * @param str The string to be checked.
     * @return True if the string is numeric and false otherwise.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String chatbotName = "Albatross\n";
        System.out.print("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");

        // Task list to store user responses
        TaskList taskList = new TaskList();

        // Scanner to read user response
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a command");

        // Scanner reads responses and adds to task list
        String userResponse = scanner.nextLine();

        // Scanner reads responses until the user enters "bye"
        while (!userResponse.equals("bye")) {
            if (userResponse.equals("list")) {
                taskList.printTaskList();
            } else if (userResponse.contains(" ")) {
                // Splits string to obtain the command and index
                String[] strings = userResponse.split(" ");

                // Checks that the command and index are valid
                if (strings[0].equals("mark") && isNumeric(strings[1])) {
                    taskList.markDone((int) Double.parseDouble(strings[1]));
                } else if (strings[0].equals("unmark") && isNumeric(strings[1])) {
                    taskList.markNotDone((int) Double.parseDouble(strings[1]));
                } else {
                    Task task = new Task(userResponse);
                    taskList.addTask(task);
                }
            } else {
                Task task = new Task(userResponse);
                taskList.addTask(task);
            }
            userResponse = scanner.nextLine();
        }

        System.out.println("Bye! Hope to see you again soon!");
        scanner.close();
    }
}
