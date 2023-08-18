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

    private static void add(String input) {
        Task task = new Task(input);
        toDoList.add(task);
        System.out.println("added: " + task.toString());
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

    private static boolean parseCommand(String command) {
        String[] parts = command.split(" ");
        
        switch (parts[0]) {
            case "bye": {
                Duke.exit();
                return false;
            }

            case "list": {
                Duke.list();
                break;
            }

            case "mark": {
                int index = Integer.parseInt(parts[1]);
                Duke.mark(index);
                break;
            }

            case "unmark": {
                int index = Integer.parseInt(parts[1]);
                Duke.unmark(index);
                break;
            }

            default: {
                Duke.add(command);
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
