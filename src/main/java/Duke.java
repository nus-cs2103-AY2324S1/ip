import java.util.Scanner;

public class Duke {
    private static ToDoList toDoList = new ToDoList();

    private static void greet() {
        System.out.println("Hello! I'm Untitled!");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void add(Task task) {
        toDoList.add(task);
        System.out.println("Got it! I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void delete(int index) {
        final Task deletedTask = toDoList.get(index);
        toDoList.delete(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void list() {
        System.out.println(toDoList);
    }

    private static void mark(int index) {
        toDoList.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toDoList.get(index));
    }

    private static void unmark(int index) {
        toDoList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(toDoList.get(index));
    }

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

    public static void main(String[] args) {
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
