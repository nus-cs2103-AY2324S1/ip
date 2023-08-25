import java.util.Scanner;

/**
 * Duke is a simple task management program that allows users to add, delete, mark, and list tasks.
 */
public class Duke {
    private static ToDoList toDoList;

    /**
     * Greets the user with a welcome message.
     */
    private static void greet() {
        System.out.println("Hello! I'm Untitled!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message when exiting the program.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds a task to the ToDoList and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    private static void add(Task task) {
        toDoList.add(task);
        System.out.println("Got it! I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    /**
     * Deletes a task at the specified index from the ToDoList and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    private static void delete(int index) {
        final Task deletedTask = toDoList.get(index);
        toDoList.delete(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    /**
     * Lists all tasks in the ToDoList and displays them.
     */
    private static void list() {
        System.out.println(toDoList);
    }

    /**
     * Marks a task at the specified index as done and displays a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    private static void mark(int index) {
        toDoList.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toDoList.get(index));
    }

    /**
     * Marks a task at the specified index as not done yet and displays a confirmation message.
     *
     * @param index The index of the task to be marked as not done yet.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    private static void unmark(int index) {
        toDoList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(toDoList.get(index));
    }

    /**
     * Parses the user's input and performs the corresponding action.
     *
     * @param input The user's input command.
     * @return {@code true} if parsing should continue, {@code false} if parsing should stop.
     * @throws IllegalArgumentException If the input command has invalid arguments.
     * @throws IndexOutOfBoundsException If the input command refers to an out-of-range index.
     */
    private static boolean parseCommand(String input) {
        // Split into command and rest
        String[] parts = input.split(" ", 2);
        final String command = parts[0];
        final String rest = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "bye": {
                Duke.exit();
                return false;
            }

            case "list": {
                Duke.list();
                break;
            }

            case "mark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.mark(index);
                break;
            }

            case "unmark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.unmark(index);
                break;
            }

            case "todo": {
                Duke.add(new ToDo(rest));
                break;
            }

            case "deadline": {
                try {
                    final String[] deadlineParts = rest.split(" /by ", 2);
                    final String name = deadlineParts[0];
                    final String endTime = deadlineParts[1];
                    Duke.add(new Deadline(name, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format. Usage: deadline <name> /by <time>");
                }
                break;
            }

            case "event": {
                try {
                    final String[] deadlineParts = rest.split(" /from ", 2);
                    final String name = deadlineParts[0];
                    final String deadline = deadlineParts[1];

                    final String[] startAndEndParts = deadline.split(" /to ", 2);
                    final String startTime = startAndEndParts[0];
                    final String endTime = startAndEndParts[1];

                    Duke.add(new Event(name, startTime, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format. Usage: event <name> /from <time> /to <time>");
                }
                break;
            }

            case "delete": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.delete(index);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown command.");
            }
        }

        return true;
    }

    /**
     * Main method to run the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        final String DATA_DIRECTORY = "../../../data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.ser";
        toDoList = new ToDoList(dataFilePath);
        Scanner scanner = new Scanner(System.in);

        Duke.greet();

        boolean continueParsing = true;
        while (continueParsing) {
            String input = scanner.nextLine();
            try {
                continueParsing = Duke.parseCommand(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
