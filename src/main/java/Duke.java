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

    private static void addToList(String input) {
        toDoList.add(input);
        System.out.println("added: " + input);
    }

    private static void list() {
        System.out.println(toDoList);
    }

    private static boolean parseCommand(String command) {
        switch (command) {
            case "bye":
                Duke.exit();
                return false;

            case "list":
                Duke.list();
                break;

            default:
                Duke.addToList(command);
                break;
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
