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
            }
            if (userResponse.contains(" ")) {
                // Split string to obtain the command and index
                String[] strings = userResponse.split(" ");

                // Check for each command and performs the corresponding actions
                if (strings[0].equals("mark") && isNumeric(strings[1])) {
                    // Obtain the index number and get the task list to mark the corresponding task
                    taskList.markDone((int) Double.parseDouble(strings[1]));
                } else if (strings[0].equals("unmark") && isNumeric(strings[1])) {
                    // Obtain the index number and get the task list to unmark the corresponding task
                    taskList.markNotDone((int) Double.parseDouble(strings[1]));
                } else if (strings[0].equals("todo")) {
                    // Add the task to the task list
                    String todoString = userResponse.substring(4).trim();
                    Todo todo = new Todo(todoString);
                    taskList.addTask(todo);
                } else if (strings[0].equals("deadline")) {
                    // Split the string to obtain the task name and deadline
                    // Add the task to the task list
                    String deadlineStr = userResponse.substring(8);
                    String[] strSegments = deadlineStr.split("/by");
                    Deadline deadline = new Deadline(strSegments[0].trim(), strSegments[1].trim());
                    taskList.addTask(deadline);
                } else if (strings[0].equals("event")) {
                    // Split the string to obtain the task name, start date and end date
                    // Add the task to the task list
                    String eventStr = userResponse.substring(5);
                    String[] strSegments = eventStr.split("/from");
                    String eventName = strSegments[0].trim();
                    String[] dates = strSegments[1].split("/to");
                    Event event = new Event(eventName, dates[0].trim(), dates[1].trim());
                    taskList.addTask(event);
                }
            }
            userResponse = scanner.nextLine();
        }

        System.out.println("Bye! Hope to see you again soon!");
        scanner.close();
    }
}
