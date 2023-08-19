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
                int index = Integer.parseInt(rest);
                Duke.mark(index);
                break;
            }

            case "unmark": {
                int index = Integer.parseInt(rest);
                Duke.unmark(index);
                break;
            }

            case "todo": {
                Duke.add(new ToDo(rest));
                break;
            }

            case "deadline": {
                final String[] deadlineParts = rest.split(" /by ", 2);
                final String name = deadlineParts[0];
                final String endTime = deadlineParts[1];
                Duke.add(new Deadline(name, endTime));
                break;
            }

            case "event": {
                final String[] deadlineParts = rest.split(" /from ", 2);
                final String name = deadlineParts[0];
                final String deadline = deadlineParts[1];

                final String[] startAndEndParts = deadline.split(" /to ", 2);
                final String startTime = startAndEndParts[0];
                final String endTime = startAndEndParts[1];

                Duke.add(new Event(name, startTime, endTime));
                break;
            }

            default: {
                // ???
                break;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke.greet();
        // Keep processing input until parse failure; i.e. exit
        for (String input = scanner.nextLine(); Duke.parseCommand(input); input = scanner.nextLine());

        scanner.close();
    }
}
